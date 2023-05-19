package me.hechenberger.healthy

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import java.security.Security

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
class HealthyApplication

fun main(args: Array<String>) {
//    java.security.Security.setProperty("networkaddress.cache.ttl" , "1");
//    java.security.Security.setProperty("networkaddress.cache.negative.ttl" , "0");
//    java.security.Security.setProperty("sun.net.inetaddr.ttl" , "0");
//    System.out.println(Security.getProperty("networkaddress.cache.ttl"));
//    System.out.println(System.getProperty("networkaddress.cache.ttl"));
//    System.out.println(Security.getProperty("networkaddress.cache.negative.ttl"));
//    System.out.println(System.getProperty("networkaddress.cache.negative.ttl"));

    SpringApplication.run(HealthyApplication::class.java, *args)
}