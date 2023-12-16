package com.daehwa.user.profile.facade_service

import com.daehwa.user.profile.dto.CreateFriendsRequest
import com.daehwa.user.profile.dto.GetProfileResponse
import com.daehwa.user.profile.service.FriendMapService
import com.daehwa.user.profile.service.ProfileService
import com.daehwa.user.profile.service.UserService

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileFacadeService(
    private val profileService: ProfileService,
    private val friendMapService: FriendMapService,
    private val userService: UserService,
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
    fun createFriends(email: String, request: CreateFriendsRequest) {
        val user = userService.getUser(email)
        val friends = userService.getUsers(request.friendEmails.toList())

        val userProfile = profileService.getProfileByUser(user)
        val friendProfiles = profileService.getProfilesByUser(friends)

        friendMapService.createFriendMaps(userProfile, friendProfiles)
    }
}
