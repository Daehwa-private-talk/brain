package com.daehwa.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ConfigurationPropertiesScan(basePackages = ["com.daehwa.core.*", "com.daehwa.chat.*"])
@ComponentScan(basePackages = ["com.daehwa.core.*", "com.daehwa.chat.*"])
@EnableDiscoveryClient
@SpringBootApplication
class ChatApplication

fun main(args: Array<String>) {
    runApplication<ChatApplication>(*args)
}
