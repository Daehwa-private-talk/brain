package com.daehwa.core.jpa


import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import java.time.LocalDateTime

@RedisHash(value = "login_user")
class LoginUser(
    @Id
    val email: String,
    val userId: Int,
    val name: String,
    val password: String,
    val roleName: String,
    @Indexed
    val refreshToken: String,
    val signInAt: LocalDateTime,
    @TimeToLive
    @Value("\${token.refresh-token-renew-hour}")
    val timeToLive: Long = 0,
)
