package com.daehwa.user.profile.service

import com.daehwa.core.exception.DaehwaException
import com.daehwa.core.exception.ErrorCode
import com.daehwa.user.common.repository.DaehwaUser
import com.daehwa.user.common.repository.Profile
import com.daehwa.user.common.repository.ProfileRepository
import com.daehwa.user.common.repository.UserRepository
import com.daehwa.user.profile.dto.CreateProfileRequest
import com.daehwa.user.profile.dto.UpdateProfileRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileService(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
) {
    @Transactional(readOnly = true)
    fun getProfileByUser(user: DaehwaUser): Profile =
        profileRepository.findByUser(user) ?: throw DaehwaException(
            ErrorCode.NOT_FOUND,
            "해당 회원의 프로필이 존재하지 않습니다 [user email: ${user.email}]",
        )

    @Transactional(readOnly = true)
    fun getProfilesById(ids: List<Int>): List<Profile> =
        profileRepository.findAllByIdIn(ids)

    @Transactional(readOnly = true)
    fun getProfilesByUser(users: List<DaehwaUser>): List<Profile> =
        profileRepository.findAllByUserIn(users)

    @Transactional(readOnly = true)
    fun getProfileByEmail(email: String): Profile =
        profileRepository.findByEmail(email) ?: throw DaehwaException(
            ErrorCode.NOT_FOUND,
            "프로필이 존재하지 않습니다. email: $email",
        )


    @Transactional
    fun updateProfile(email: String, request: UpdateProfileRequest) {
        val (nickname, statusMessage, emoji) = request
        val profile = getProfileByEmail(email)

        profile.nickname = nickname
        profile.statusMessage = statusMessage
        profile.emoji = emoji
    }

    @Transactional
    fun createProfile(email: String, request: CreateProfileRequest) {
        val user = userRepository.findByEmail(email)
            ?: throw DaehwaException(
                ErrorCode.NOT_FOUND,
                "유저가 존재하지 않습니다. email: $email",
            )

        validateDuplicate(user)

        val profile = Profile(
            email = email,
            nickname = request.nickname,
            statusMessage = request.statusMessage,
            user = user,
            emoji = request.emoji,
        )

        profileRepository.save(profile)
    }

    private fun validateDuplicate(user: DaehwaUser) {
        if (profileRepository.existsByUser(user)) {
            throw DaehwaException(ErrorCode.DUPLICATED, "해당 회원의 프로필이 이미 존재합니다. user email: ${user.email}")
        }
    }

    @Transactional
    fun updateImage(email: String, imageFileName: String?, imagePreSignedUrl: String) {
        val profile = getProfileByEmail(email)

        profile.imageUrl = imagePreSignedUrl
        profile.imageFileName = imageFileName
    }
}
