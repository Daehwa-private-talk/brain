package com.daehwa.user.member.service

import com.daehwa.user.common.jpa.FriendMapRepository
import com.daehwa.user.common.jpa.UserRepository
import org.springframework.stereotype.Service

@Service
class MemberService(private val friendMapRepository: FriendMapRepository) {
    fun getFriends(userId: Int) {
        val friendMaps = friendMapRepository.findByUserId(userId)
    }
}
