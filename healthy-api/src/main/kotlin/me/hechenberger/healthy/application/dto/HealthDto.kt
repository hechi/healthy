package me.hechenberger.healthy.application.dto

import me.hechenberger.healthy.domain.health.Health
import me.hechenberger.healthy.domain.health.Status

class HealthDto constructor(var name: String, var status: Status) {

    companion object {
        fun toApplicationDto(health: Health): HealthDto {
            return HealthDto(
                health.name,
                health.status
            )
        }
    }
}
