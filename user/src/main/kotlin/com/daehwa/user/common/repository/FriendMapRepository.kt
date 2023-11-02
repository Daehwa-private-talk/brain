package com.daehwa.user.common.repository

import org.springframework.data.jpa.repository.JpaRepository

interface FriendMapRepository: JpaRepository<FriendMap, Int> {
    fun findAllByUserProfile(userProfile: Profile): List<FriendMap>
}
