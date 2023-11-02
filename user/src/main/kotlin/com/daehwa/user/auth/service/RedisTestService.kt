package com.daehwa.user.auth.service

import com.daehwa.user.common.repository.LoginUser
import com.daehwa.user.common.repository.LoginUserRepository
import org.springframework.stereotype.Service

private const val email = "test@gmail.com"

@Service
class RedisTestService(
    private val loginUserRepository: LoginUserRepository,
) {
    fun save() {
        val user = LoginUser(email, "refreshtoken")
        loginUserRepository.save(user)
    }

    fun get(): LoginUser? {
        return loginUserRepository.findAllByEmail(email)
    }
}
