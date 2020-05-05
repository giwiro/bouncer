package com.retaily.bouncer.common.log

import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.EnumerablePropertySource
import org.springframework.stereotype.Component


@Component
class PropertiesLogger {
    // companion object : KLogging()

    @EventListener
    fun handleContextRefreshed(event: ContextRefreshedEvent) {
        printActiveProperties(event.applicationContext.environment as ConfigurableEnvironment)
    }

    fun printActiveProperties(env: ConfigurableEnvironment) {
        println("************************* ACTIVE APP PROPERTIES ******************************")
        env.propertySources
                .filter { it.name.contains("applicationConfig") }
                .map { it as EnumerablePropertySource<*> }
                .map { it -> it.propertyNames.toList() }
                .flatMap { it }
                .distinctBy { it }
                .sortedBy { it }
                .forEach { it ->
                    try {
                        println("$it=${env.getProperty(it)}")
                    } catch (e: Exception) {
                        // logger.warn("$it -> ${e.message}")
                    }
                }
        println("******************************************************************************")
    }
}