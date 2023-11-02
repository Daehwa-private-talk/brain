package com.daehwa.user.member.service

import com.daehwa.core.exception.DaehwaException
import com.daehwa.core.exception.ErrorCode
import com.daehwa.user.common.repository.Profile
import com.daehwa.user.common.repository.ProfileRepository
import com.daehwa.user.member.dto.CreateProfileRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileService(
    private val profileRepository: ProfileRepository,
) {
    @Transactional(readOnly = true)
    fun getProfile(userId: Int): Profile =
        profileRepository.findByUserId(userId) ?: throw DaehwaException(
            ErrorCode.NOT_FOUND,
            "해당 회원의 프로필이 존재하지 않습니다 [user id: $userId]",
        )

    @Transactional(readOnly = true)
    fun getProfiles(ids: List<Int>): List<Profile> =
        profileRepository.findAllByIdIn(ids)

    @Transactional
    fun createProfile(userId: Int, request: CreateProfileRequest) {
        val profile = Profile(
            nickname = request.nickname,
            image = request.image,
            statusMessage = request.statusMessage,
            userId = userId,
        )
        validateDuplicate(userId)

        profileRepository.save(profile)
    }

    private fun validateDuplicate(userId: Int) {
        if (profileRepository.existsByUserId(userId)) {
            throw DaehwaException(ErrorCode.DUPLICATED, "해당 회원의 프로필이 이미 존재합니다. user id: $userId")
        }
    }
}
