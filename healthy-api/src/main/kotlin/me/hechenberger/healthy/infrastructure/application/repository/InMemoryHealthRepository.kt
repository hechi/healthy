package me.hechenberger.healthy.infrastructure.application.repository

import me.hechenberger.healthy.domain.health.Health
import me.hechenberger.healthy.domain.health.Status
import me.hechenberger.healthy.domain.health.repository.HealthRepository
import mu.KotlinLogging
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

private val log = KotlinLogging.logger {}

@Repository
class InMemoryHealthRepository :
    HealthRepository {
    var applications: Map<String, Health> = mutableMapOf()

    override fun getApplications(): List<Health> {
        return applications.values.toList()
    }

    override fun get(name: String): Health {
        return applications.getValue(name)
    }

    override fun save(
        name: String,
        status: Status,
        url: String,
        upHttpCode: List<Int>,
        downHttpCode: List<Int>,
        responseTimeInMillis: Long,
        timestamp: LocalDateTime
    ) {
        applications = applications.plus(
            Pair(
                name,
                Health(name, status, url, upHttpCode, downHttpCode, responseTimeInMillis, timestamp)
            )
        )
    }
}