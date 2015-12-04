package org.gradle.sample.plugin;

import org.gradle.tooling.model.Model;
import org.gradle.tooling.model.DomainObjectSet;

/**
 * This is a custom tooling model. It must be assignable to {@link main.java.com.mule.Model} and it must be an interface.
 */
public interface CustomModel extends Model {
    String getName();

    DomainObjectSet<String> getTasks();
}
