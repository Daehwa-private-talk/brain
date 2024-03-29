package com.daehwa.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@EnableRedisRepositories(basePackages = ["com.daehwa.core.*"])
class RedisConfig(
    private val property: RedisProperty
) {
    @Bean
    fun redisConnectionFactory() = LettuceConnectionFactory(property.host, property.port)
}
