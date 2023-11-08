package com.daehwa.user.auth.service

import com.daehwa.core.jpa.LoginUser
import com.daehwa.core.jpa.LoginUserRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

private const val email = "test@gmail.com"
private const val email2 = "test@test.com"
@Service
class RedisTestService(
    private val loginUserRepository: LoginUserRepository,
) {
    fun save() {
//        val user = LoginUser(email, "refreshtoken")
//        val user2 = LoginUser(email2, "accesstoken")

//        loginUserRepository.saveAll(listOf(user, user2))
    }

    fun get(): LoginUser? {
        return loginUserRepository.findById(email2).getOrNull()
    }
}
