package com.daehwa.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ConfigurationPropertiesScan(basePackages = ["com.daehwa.core.*"])
@ComponentScan(basePackages = ["com.daehwa.core.*", "com.daehwa.user.*"])
@EnableJpaRepositories(basePackages = ["com.daehwa.core.*", "com.daehwa.user.*"])
@EntityScan(basePackages = ["com.daehwa.core.*", "com.daehwa.user.*"])
@EnableDiscoveryClient
@SpringBootApplication
class UserApplication

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}
