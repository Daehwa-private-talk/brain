package com.daehwa.user.common.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LoginUserRepository : CrudRepository<LoginUser, String> {
    //    fun findAllById(email: String)
    fun findAllByEmail(email: String): LoginUser
}
