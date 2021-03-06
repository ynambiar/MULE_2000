/*
 * Copyright 2010 the original author or authors.
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
package org.gradle.api.internal.artifacts.ivyservice.moduleconverter;

import org.gradle.api.artifacts.Configuration;
import org.gradle.api.internal.artifacts.configurations.Configurations;
import org.gradle.api.internal.artifacts.configurations.DirectBuildDependencies;
import org.gradle.api.tasks.TaskDependency;
import org.gradle.internal.component.local.model.BuildableLocalComponentMetaData;

import java.util.Set;

public class DefaultConfigurationsToModuleDescriptorConverter implements ConfigurationsToModuleDescriptorConverter {
    public void addConfigurations(BuildableLocalComponentMetaData metaData, Iterable<? extends Configuration> configurations) {
        for (Configuration configuration : configurations) {
            addConfiguration(metaData, configuration);
        }
    }

    private void addConfiguration(BuildableLocalComponentMetaData metaData, Configuration configuration) {
        Set<String> hierarchy = Configurations.getNames(configuration.getHierarchy());
        Set<String> extendsFrom = Configurations.getNames(configuration.getExtendsFrom());
        TaskDependency directBuildDependencies = DirectBuildDependencies.forDependenciesAndArtifacts(configuration);
        metaData.addConfiguration(configuration.getName(), configuration.getDescription(), extendsFrom, hierarchy, configuration.isVisible(), configuration.isTransitive(), directBuildDependencies);
    }
}
