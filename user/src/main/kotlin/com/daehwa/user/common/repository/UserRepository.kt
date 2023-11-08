package com.daehwa.user.common.repository

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<DaehwaUser, Int> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): DaehwaUser?
}
