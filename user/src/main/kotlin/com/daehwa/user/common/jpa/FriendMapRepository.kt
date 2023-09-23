package com.daehwa.user.common.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface FriendMapRepository: JpaRepository<FriendMap, Int> {
    fun findByUserId(userId: Int): List<FriendMap>
}
