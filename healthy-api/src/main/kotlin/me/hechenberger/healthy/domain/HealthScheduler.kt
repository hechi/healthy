package me.hechenberger.healthy.domain

import fuel.Fuel
import fuel.get
import kotlinx.coroutines.*
import me.hechenberger.healthy.domain.health.repository.HealthRepository
import mu.two.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.net.InetAddress
import java.net.URI
import java.time.Instant
import kotlin.system.measureTimeMillis


private val log = KotlinLogging.logger {}

@Service
class HealthScheduler @Autowired constructor(var healthRepository: HealthRepository) {

    // TODO: make it configurable
    var timeoutInMilliSec = 1300L

    /**
     * This @Schedule annotation run every 5 seconds in this case. It can also
     * take a cron like syntax.
     * See https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
     */
    // TODO: make it configurable
    @Scheduled(fixedRate = 5000)
    fun checkHealth() {
        healthRepository.getApplications().map { app ->
            GlobalScope.async(Dispatchers.Default) {
                var statusCode = Int.MIN_VALUE
                var responseTimeInMillis: Long = -1
                try {
                    withTimeout(timeoutInMilliSec) {
                        log.info("start request -> ${app.name}")
                        responseTimeInMillis = measureTimeMillis {
                            statusCode = requestUrl(app.url)
                        }
                        log.info("${app.name} response with status code ${statusCode} took ${responseTimeInMillis}ms")
                    }
                } catch (e: java.lang.Exception) {
                    println("timeout hit in $app")
                }
                // TODO: refactor this part
                app.updateStatus(statusCode)
                app.addTimeStamp(Instant.now(),responseTimeInMillis)
                app.timestamp = Instant.now()
                healthRepository.save(app.name, app.status, app.url, app.upHttpCode, app.downHttpCode, app.responsesTimeInMillis, app.timestamp)
            }
        }
    }
}

suspend fun requestUrl(url: String): Int {
    val urlIp = InetAddress.getByName(URI.create(url).host)
    log.info { "$url ${urlIp.getHostAddress()}" }
    return Fuel
        .get(url)
        .statusCode
}