package me.hechenberger.healthy.application.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.google.common.io.Resources
import me.hechenberger.healthy.domain.health.Health
import me.hechenberger.healthy.domain.health.Status
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Service
import java.net.URL
import java.time.Instant

private val log = KotlinLogging.logger {}

@PropertySource("classpath:/application.yaml")
@Service
class ConfigurationService(@Value("\${healthy.config.location}") var location: String) {
    val endpoints: Endpoints

    init {
        log.info { "load configuration from location: ${location}" }
        endpoints = loadFromFile(location)
        log.info { "${endpoints}" }
    }

    fun getEndpoints(): List<Health> {
        return endpoints.endpoints.map { endpoint ->
            Health(
                endpoint.name,
                Status.UNKNOWN,
                endpoint.url,
                endpoint.upHttpCode,
                endpoint.downHttpCode,
                0,
                Instant.MIN
            )
        }
    }
}

fun loadFromFile(configFile: String): Endpoints {
    val mapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing
    mapper.registerModule(KotlinModule()) // Enable Kotlin support
    val url: URL = Resources.getResource(configFile)
    return Resources.asByteSource(url).openBufferedStream().use {
        mapper.readValue(it, Endpoints::class.java)
    }
}
