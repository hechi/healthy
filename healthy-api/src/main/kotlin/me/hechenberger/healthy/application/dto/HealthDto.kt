package me.hechenberger.healthy.application.dto

import me.hechenberger.healthy.domain.health.Health
import me.hechenberger.healthy.domain.health.Status
import java.time.Instant

class HealthDto(
    var name: String,
    var status: Status,
    var responseTimeInMillis: Long,
    var timestamp: Instant
) {

    companion object {
        fun toApplicationDto(health: Health): HealthDto {
            return HealthDto(
                health.name,
                health.status,
                health.responseTimeInMillis,
                health.timestamp
            )
        }
    }
}
