package me.hechenberger.healthy.application.dto

import me.hechenberger.healthy.domain.health.Health
import me.hechenberger.healthy.domain.health.Status
import java.time.Instant

class HealthDto(
    var name: String,
    var status: Status,
    var responsesTimeInMillis: Array<ResponseTimeDto>,
    var timestamp: Instant
) {

    companion object {
        fun toApplicationDto(health: Health): HealthDto {
            return HealthDto(
                health.name,
                health.status,
                ResponseTimeDto.toApplicationDto(health.responsesTimeInMillis),
                health.timestamp
            )
        }
    }
}

class ResponseTimeDto(
    var timestamp: Instant,
    var responseTime: Long
) {
    companion object {
        fun toApplicationDto(response: Map<Instant, Long>): Array<ResponseTimeDto> {
            var responses: Array<ResponseTimeDto> = response.map { ResponseTimeDto(it.key, it.value)}.toTypedArray()
            return responses
        }
    }
}
