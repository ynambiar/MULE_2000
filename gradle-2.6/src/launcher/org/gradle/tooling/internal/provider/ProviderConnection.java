/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.tooling.internal.provider;

import org.gradle.StartParameter;
import org.gradle.api.logging.LogLevel;
import org.gradle.initialization.*;
import org.gradle.internal.Factory;
import org.gradle.internal.invocation.BuildAction;
import org.gradle.internal.service.ServiceRegistry;
import org.gradle.launcher.cli.converter.LayoutToPropertiesConverter;
import org.gradle.launcher.cli.converter.PropertiesToDaemonParametersConverter;
import org.gradle.launcher.daemon.client.DaemonClient;
import org.gradle.launcher.daemon.client.DaemonClientFactory;
import org.gradle.launcher.daemon.configuration.DaemonParameters;
import org.gradle.launcher.exec.BuildActionExecuter;
import org.gradle.launcher.exec.BuildActionParameters;
import org.gradle.logging.LoggingManagerInternal;
import org.gradle.logging.LoggingServiceRegistry;
import org.gradle.logging.internal.OutputEventListener;
import org.gradle.logging.internal.OutputEventRenderer;
import org.gradle.process.internal.streams.SafeStreams;
import org.gradle.tooling.internal.build.DefaultBuildEnvironment;
import org.gradle.tooling.internal.consumer.parameters.FailsafeBuildProgressListenerAdapter;
import org.gradle.tooling.internal.consumer.versioning.ModelMapping;
import org.gradle.tooling.internal.protocol.*;
import org.gradle.tooling.internal.protocol.events.InternalProgressEvent;
import org.gradle.tooling.internal.protocol.test.InternalTestExecutionRequest;
import org.gradle.tooling.internal.provider.connection.ProviderConnectionParameters;
import org.gradle.tooling.internal.provider.connection.ProviderOperationParameters;
import org.gradle.util.GradleVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProviderConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderConnection.class);
    private final PayloadSerializer payloadSerializer;
    private final LoggingServiceRegistry loggingServices;
    private final DaemonClientFactory daemonClientFactory;
    private final BuildActionExecuter<BuildActionParameters> embeddedExecutor;
    private final ServiceRegistry sharedServices;

    public ProviderConnection(ServiceRegistry sharedServices, LoggingServiceRegistry loggingServices, DaemonClientFactory daemonClientFactory, BuildActionExecuter<BuildActionParameters> embeddedExecutor, PayloadSerializer payloadSerializer) {
        this.loggingServices = loggingServices;
        this.daemonClientFactory = daemonClientFactory;
        this.embeddedExecutor = embeddedExecutor;
        this.payloadSerializer = payloadSerializer;
        this.sharedServices = sharedServices;
    }

    public void configure(ProviderConnectionParameters parameters) {
        LogLevel providerLogLevel = parameters.getVerboseLogging() ? LogLevel.DEBUG : LogLevel.INFO;
        LOGGER.debug("Configuring logging to level: {}", providerLogLevel);
        LoggingManagerInternal loggingManager = loggingServices.newInstance(LoggingManagerInternal.class);
        loggingManager.setLevel(providerLogLevel);
        loggingManager.start();
    }

    public Object run(String modelName, BuildCancellationToken cancellationToken, ProviderOperationParameters providerParameters) {
        List<String> tasks = providerParameters.getTasks();
        if (modelName.equals(ModelIdentifier.NULL_MODEL) && tasks == null) {
            throw new IllegalArgumentException("No model type or tasks specified.");
        }
        Parameters params = initParams(providerParameters);
        Class<?> type = new ModelMapping().getProtocolTypeFromModelName(modelName);
        if (type == InternalBuildEnvironment.class) {
            //we don't really need to launch the daemon to acquire information needed for BuildEnvironment
            if (tasks != null) {
                throw new IllegalArgumentException("Cannot run tasks and fetch the build environment model.");
            }
            return new DefaultBuildEnvironment(
                    params.gradleUserhome,
                    GradleVersion.current().getVersion(),
                    params.daemonParams.getEffectiveJavaHome(),
                    params.daemonParams.getEffectiveJvmArgs());
        }

        StartParameter startParameter = new ProviderStartParameterConverter().toStartParameter(providerParameters, params.properties);
        ProgressListenerConfiguration listenerConfig = ProgressListenerConfiguration.from(providerParameters);
        BuildAction action = new BuildModelAction(startParameter, modelName, tasks != null, listenerConfig.clientSubscriptions);
        return run(action, cancellationToken, listenerConfig, providerParameters, params);
    }

    public Object run(InternalBuildAction<?> clientAction, BuildCancellationToken cancellationToken, ProviderOperationParameters providerParameters) {
        SerializedPayload serializedAction = payloadSerializer.serialize(clientAction);
        Parameters params = initParams(providerParameters);
        StartParameter startParameter = new ProviderStartParameterConverter().toStartParameter(providerParameters, params.properties);
        ProgressListenerConfiguration listenerConfig = ProgressListenerConfiguration.from(providerParameters);
        BuildAction action = new ClientProvidedBuildAction(startParameter, serializedAction, listenerConfig.clientSubscriptions);
        return run(action, cancellationToken, listenerConfig, providerParameters, params);
    }

    public Object runTests(InternalTestExecutionRequest testExecutionRequest, BuildCancellationToken cancellationToken, ProviderOperationParameters providerParameters) {
        Parameters params = initParams(providerParameters);
        StartParameter startParameter = new ProviderStartParameterConverter().toStartParameter(providerParameters, params.properties);
        ProgressListenerConfiguration listenerConfig = ProgressListenerConfiguration.from(providerParameters);
        TestExecutionRequestAction action = new TestExecutionRequestAction(testExecutionRequest, startParameter, listenerConfig.clientSubscriptions);
        return run(action, cancellationToken, listenerConfig, providerParameters, params);
    }

    private Object run(BuildAction action, BuildCancellationToken cancellationToken, ProgressListenerConfiguration progressListenerConfiguration, ProviderOperationParameters providerParameters, Parameters parameters) {
        try {
            BuildActionExecuter<ProviderOperationParameters> executer = createExecuter(providerParameters, parameters);
            BuildRequestContext buildRequestContext = new DefaultBuildRequestContext(new DefaultBuildRequestMetaData(providerParameters.getStartTime()), cancellationToken, progressListenerConfiguration.buildEventConsumer);
            BuildActionResult result = (BuildActionResult) executer.execute(action, buildRequestContext, providerParameters, sharedServices);
            if (result.failure != null) {
                throw (RuntimeException) payloadSerializer.deserialize(result.failure);
            }
            return payloadSerializer.deserialize(result.result);
        } finally {
            progressListenerConfiguration.failsafeWrapper.rethrowErrors();
        }
    }

    private BuildActionExecuter<ProviderOperationParameters> createExecuter(ProviderOperationParameters operationParameters, Parameters params) {
        LoggingServiceRegistry loggingServices;
        BuildActionExecuter<BuildActionParameters> executer;
        if (Boolean.TRUE.equals(operationParameters.isEmbedded())) {
            loggingServices = this.loggingServices;
            executer = embeddedExecutor;
        } else {
            loggingServices = LoggingServiceRegistry.newNestedLogging();
            loggingServices.get(OutputEventRenderer.class).configure(operationParameters.getBuildLogLevel());
            ServiceRegistry clientServices = daemonClientFactory.createBuildClientServices(loggingServices.get(OutputEventListener.class), params.daemonParams, operationParameters.getStandardInput(SafeStreams.emptyInput()));
            executer = clientServices.get(DaemonClient.class);
        }
        Factory<LoggingManagerInternal> loggingManagerFactory = loggingServices.getFactory(LoggingManagerInternal.class);
        return new LoggingBridgingBuildActionExecuter(new DaemonBuildActionExecuter(executer, params.daemonParams), loggingManagerFactory);
    }

    private Parameters initParams(ProviderOperationParameters operationParameters) {
        BuildLayoutParameters layout = new BuildLayoutParameters();
        if (operationParameters.getGradleUserHomeDir() != null) {
            layout.setGradleUserHomeDir(operationParameters.getGradleUserHomeDir());
        }
        layout.setSearchUpwards(operationParameters.isSearchUpwards() != null ? operationParameters.isSearchUpwards() : true);
        layout.setProjectDir(operationParameters.getProjectDir());

        Map<String, String> properties = new HashMap<String, String>();
        new LayoutToPropertiesConverter().convert(layout, properties);

        DaemonParameters daemonParams = new DaemonParameters(layout);
        new PropertiesToDaemonParametersConverter().convert(properties, daemonParams);
        if (operationParameters.getDaemonBaseDir(null) != null) {
            daemonParams.setBaseDir(operationParameters.getDaemonBaseDir(null));
        }

        //override the params with the explicit settings provided by the tooling api
        List<String> defaultJvmArgs = daemonParams.getAllJvmArgs();
        daemonParams.setJvmArgs(operationParameters.getJvmArguments(defaultJvmArgs));
        File defaultJavaHome = daemonParams.getEffectiveJavaHome();
        daemonParams.setJavaHome(operationParameters.getJavaHome(defaultJavaHome));

        if (operationParameters.getDaemonMaxIdleTimeValue() != null && operationParameters.getDaemonMaxIdleTimeUnits() != null) {
            int idleTimeout = (int) operationParameters.getDaemonMaxIdleTimeUnits().toMillis(operationParameters.getDaemonMaxIdleTimeValue());
            daemonParams.setIdleTimeout(idleTimeout);
        }

        return new Parameters(daemonParams, properties, layout.getGradleUserHomeDir());
    }

    private static class Parameters {
        DaemonParameters daemonParams;
        Map<String, String> properties;
        File gradleUserhome;

        public Parameters(DaemonParameters daemonParams, Map<String, String> properties, File gradleUserhome) {
            this.daemonParams = daemonParams;
            this.properties = properties;
            this.gradleUserhome = gradleUserhome;
        }
    }

    private static final class BuildProgressListenerInvokingBuildEventConsumer implements BuildEventConsumer {
        private final InternalBuildProgressListener buildProgressListener;

        private BuildProgressListenerInvokingBuildEventConsumer(InternalBuildProgressListener buildProgressListener) {
            this.buildProgressListener = buildProgressListener;
        }

        @Override
        public void dispatch(Object event) {
            if (event instanceof InternalProgressEvent) {
                this.buildProgressListener.onEvent(event);
            }
        }
    }

    private static final class ProgressListenerConfiguration {
        private final BuildClientSubscriptions clientSubscriptions;
        private final FailsafeBuildProgressListenerAdapter failsafeWrapper;
        private final BuildEventConsumer buildEventConsumer;

        public ProgressListenerConfiguration(BuildClientSubscriptions clientSubscriptions, BuildEventConsumer buildEventConsumer, FailsafeBuildProgressListenerAdapter failsafeWrapper) {
            this.clientSubscriptions = clientSubscriptions;
            this.buildEventConsumer = buildEventConsumer;
            this.failsafeWrapper = failsafeWrapper;
        }

        private static ProgressListenerConfiguration from(ProviderOperationParameters providerParameters) {
            InternalBuildProgressListener buildProgressListener = providerParameters.getBuildProgressListener(null);
            boolean listenToTestProgress = buildProgressListener != null && buildProgressListener.getSubscribedOperations().contains(InternalBuildProgressListener.TEST_EXECUTION);
            boolean listenToTaskProgress = buildProgressListener != null && buildProgressListener.getSubscribedOperations().contains(InternalBuildProgressListener.TASK_EXECUTION);
            boolean listenToBuildProgress = buildProgressListener != null && buildProgressListener.getSubscribedOperations().contains(InternalBuildProgressListener.BUILD_EXECUTION);
            BuildClientSubscriptions clientSubscriptions = new BuildClientSubscriptions(listenToTestProgress, listenToTaskProgress, listenToBuildProgress);
            FailsafeBuildProgressListenerAdapter wrapper = new FailsafeBuildProgressListenerAdapter(buildProgressListener);
            BuildEventConsumer buildEventConsumer = clientSubscriptions.isSendAnyProgressEvents() ? new BuildProgressListenerInvokingBuildEventConsumer(wrapper) : new NoOpBuildEventConsumer();
            if (Boolean.TRUE.equals(providerParameters.isEmbedded())) {
                // Contract requires build events are delivered by a single thread. This is taken care of by the daemon client when not in embedded mode
                // Need to apply some synchronization when in embedded mode
                buildEventConsumer = new SynchronizedConsumer(buildEventConsumer);
            }
            return new ProgressListenerConfiguration(clientSubscriptions, buildEventConsumer, wrapper);
        }

        private static class SynchronizedConsumer implements BuildEventConsumer {
            private final BuildEventConsumer delegate;

            public SynchronizedConsumer(BuildEventConsumer delegate) {
                this.delegate = delegate;
            }

            @Override
            public void dispatch(Object message) {
                synchronized (this) {
                    delegate.dispatch(message);
                }
            }
        }
    }
}