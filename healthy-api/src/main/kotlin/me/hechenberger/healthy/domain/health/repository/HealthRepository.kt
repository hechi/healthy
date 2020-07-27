package me.hechenberger.healthy.domain.health.repository

import me.hechenberger.healthy.domain.health.Health
import me.hechenberger.healthy.domain.health.Status

interface HealthRepository {

    fun getApplications(): List<Health>

    fun get(name: String): Health

    fun update(name: String, status: Status)

    fun save(
        name: String,
        status: Status,
        url: String,
        upHttpCode: List<Int>,
        downHttpCode: List<Int>
    )
}