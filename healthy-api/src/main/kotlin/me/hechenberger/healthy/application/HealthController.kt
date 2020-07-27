package me.hechenberger.healthy.application

import me.hechenberger.healthy.application.configuration.ConfigurationService
import me.hechenberger.healthy.application.dto.HealthDto
import me.hechenberger.healthy.domain.health.repository.HealthRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController @Autowired constructor(
    configurationService: ConfigurationService,
    var healthRepository: HealthRepository
) {

    init {
        configurationService.getEndpoints()
            .forEach { app -> healthRepository.save(app.name, app.status, app.url, app.upHttpCode, app.downHttpCode) }
    }

    @GetMapping("/health")
    fun healthStatus(@RequestParam(value = "name", defaultValue = "World") name: String): List<HealthDto> {
        return healthRepository.getApplications().map { app -> HealthDto.toApplicationDto(app) }
    }

}