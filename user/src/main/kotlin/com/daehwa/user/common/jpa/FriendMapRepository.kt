package com.daehwa.user.common.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface FriendMapRepository: JpaRepository<FriendMap, Int> {
    fun findAllByUserProfile(userProfile: Profile): List<FriendMap>
}
