package me.hechenberger.healthy.domain.health.repository

import me.hechenberger.healthy.domain.health.Health
import me.hechenberger.healthy.domain.health.Status
import java.time.Instant

interface HealthRepository {

    fun getApplications(): List<Health>

    fun get(name: String): Health

    fun save(
        name: String,
        status: Status,
        url: String,
        upHttpCode: List<Int>,
        downHttpCode: List<Int>,
        responsesTimeInMillis: Map<Instant,Long>,
        timestampInMillis: Instant
    )
}