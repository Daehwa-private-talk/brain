package com.daehwa.core.model

import org.springframework.data.repository.CrudRepository

interface LoginUserRepository : CrudRepository<LoginUser, String> {
    fun findByRefreshToken(refreshToken: String): LoginUser?
}
