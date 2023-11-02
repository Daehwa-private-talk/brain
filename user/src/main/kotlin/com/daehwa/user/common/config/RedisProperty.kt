package com.daehwa.user.common.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "spring.data.redis")
class RedisProperty {
    var port: Int = 6379
    var host: String = "localhost"
}
