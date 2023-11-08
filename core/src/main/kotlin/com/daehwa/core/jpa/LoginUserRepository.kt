package com.daehwa.core.jpa

import org.springframework.data.repository.CrudRepository

interface LoginUserRepository : CrudRepository<LoginUser, String> {
    fun findByEmail(email: String): LoginUser?
    fun findByRefreshToken(refreshToken: String): LoginUser?
}
