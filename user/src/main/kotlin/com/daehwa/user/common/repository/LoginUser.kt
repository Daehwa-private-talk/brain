package com.daehwa.user.common.repository

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "login_user")
data class LoginUser(
    @Id
    val email: String,
    val refreshToken: String,
)
