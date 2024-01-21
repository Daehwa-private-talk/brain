package com.daehwa.user.profile.facade_service

import com.daehwa.user.profile.dto.CreateFriendsRequest
import com.daehwa.user.profile.dto.GetProfileResponse
import com.daehwa.user.profile.service.FriendMapService
import com.daehwa.user.profile.service.S3Service
import com.daehwa.user.profile.service.ProfileService
import com.daehwa.user.profile.service.UserService

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ProfileFacadeService(
    private val profileService: ProfileService,
    private val friendMapService: FriendMapService,
    private val userService: UserService,
    private val s3Service: S3Service,
) {

    @Transactional(readOnly = true)
    fun getMyProfile(email: String): GetProfileResponse {
        val user = userService.getUser(email)

        return GetProfileResponse(profileService.getProfileByUser(user))
    }

    @Transactional(readOnly = true)
    fun getProfiles(email: String): List<GetProfileResponse> {
        val user = userService.getUser(email)

        val profile = profileService.getProfileByUser(user)
        val friendMaps = friendMapService.getFriendMaps(profile)
        val friends = profileService.getProfilesById(friendMaps.map { it.friendProfile.id })

        return friends.map { GetProfileResponse(it) }
    }

    @Transactional
    fun uploadProfileImage(email: String, image: MultipartFile) {
        val imagePreSignedUrl = s3Service.save(image)
        profileService.updateImage(email, image.originalFilename, imagePreSignedUrl)
    }

    @Transactional
    fun updateProfileImage(email: String, image: MultipartFile) {
        deletePreviousProfileImage(email)

        uploadProfileImage(email, image)
    }

    private fun deletePreviousProfileImage(email: String) {
        val profile = profileService.getProfileByEmail(email)
        profile.imageFileName ?: return

        s3Service.delete(profile.imageFileName!!)
    }

    @Transactional
    fun createFriends(email: String, request: CreateFriendsRequest) {
        val user = userService.getUser(email)
        val friends = userService.getUsers(request.friendEmails.toList())

        val userProfile = profileService.getProfileByUser(user)
        val friendProfiles = profileService.getProfilesByUser(friends)

        friendMapService.createFriendMaps(userProfile, friendProfiles)
    }
}
