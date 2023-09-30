package com.daehwa.user.common.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Int> {
    fun findByUserId(userId: Int): Profile?
    fun findByUserIdIn(userIds: List<Int>): List<Profile>
    fun findAllByIdIn(ids: List<Int>): List<Profile>
}
