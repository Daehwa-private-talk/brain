package com.daehwa.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "token")
class TokenProperty {
    var secretKey: String = ""
    var accessTokenExpireTime: Long = 1L
    var refreshTokenRenewHour: Long = 1L
}
