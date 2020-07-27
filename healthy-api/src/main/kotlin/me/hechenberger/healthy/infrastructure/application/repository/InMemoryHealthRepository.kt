package me.hechenberger.healthy.infrastructure.application.repository

import me.hechenberger.healthy.domain.health.Health
import me.hechenberger.healthy.domain.health.Status
import me.hechenberger.healthy.domain.health.repository.HealthRepository
import mu.KotlinLogging
import org.springframework.stereotype.Repository

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

    override fun update(name: String, status: Status) {
        applications.getValue(name).status = status
        log.info("${name} is ${status}")
    }

    override fun save(
        name: String,
        status: Status,
        url: String,
        upHttpCode: List<Int>,
        downHttpCode: List<Int>
    ) {
        applications = applications.plus(
            Pair(
                name,
                Health(name, status, url, upHttpCode, downHttpCode)
            )
        )
    }
}