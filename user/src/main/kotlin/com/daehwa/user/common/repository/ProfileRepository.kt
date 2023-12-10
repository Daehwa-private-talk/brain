package com.daehwa.user.common.repository

import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Int> {
    fun findByUser(user: DaehwaUser): Profile?
    fun findAllByIdIn(ids: List<Int>): List<Profile>
    fun findAllByUserIn(users: List<DaehwaUser>): List<Profile>
    fun existsByUser(user: DaehwaUser): Boolean
}
