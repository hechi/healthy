package me.hechenberger.healthy.domain

import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.*
import me.hechenberger.healthy.domain.health.repository.HealthRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.net.InetAddress
import java.net.URI
import java.net.URL


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
                try {
                    withTimeout(timeoutInMilliSec) {
                        log.info("start request -> ${app.name}")
                        var statusCode: Int = requestUrl(app.url)
                        log.info("${app.name} response with status code ${statusCode}")
                        app.updateStatus(statusCode)
                        healthRepository.update(app.name, app.status)
                    }
                } catch (e: java.lang.Exception) {
                    println("timeout hit in $app")
                    app.updateStatus(Int.MIN_VALUE)
                    healthRepository.update(app.name, app.status)
                }
            }
        }
    }
}

fun requestUrl(url: String): Int {
    val urlIp = InetAddress.getByName(URI.create(url).host)
    log.info { "$url ${urlIp.getHostAddress()}" }
    val (request, response, result) = Fuel
            .reset()
            .get(url)
            .timeout(100)
            .useHttpCache(false).responseString()
    return response.statusCode
}