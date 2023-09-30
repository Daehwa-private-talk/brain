package com.daehwa.user.member.service

import com.daehwa.core.exception.DaehwaException
import com.daehwa.core.exception.ErrorCode
import com.daehwa.user.common.jpa.FriendMapRepository
import com.daehwa.user.common.jpa.Profile
import com.daehwa.user.common.jpa.ProfileRepository
import org.springframework.stereotype.Service

@Service
class ProfileService(
    private val friendMapRepository: FriendMapRepository,
    private val profileRepository: ProfileRepository,
) {
    fun getProfile(userId: Int): Profile =
        profileRepository.findByUserId(userId) ?: throw DaehwaException(
            ErrorCode.NOT_FOUND,
            "해당 회원이 존재하지 않습니다 [user id: $userId]",
        )

    fun getProfiles(ids: List<Int>): List<Profile> =
        profileRepository.findAllByIdIn(ids)
}
