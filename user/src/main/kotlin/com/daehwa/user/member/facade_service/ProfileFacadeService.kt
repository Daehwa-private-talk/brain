package com.daehwa.user.member.facade_service

import com.daehwa.user.member.dto.CreateFriendsRequest
import com.daehwa.user.member.dto.GetMemberResponse
import com.daehwa.user.member.service.FriendMapService
import com.daehwa.user.member.service.ProfileService
import com.daehwa.user.member.service.UserService

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileFacadeService(
    private val profileService: ProfileService,
    private val friendMapService: FriendMapService,
    private val userService: UserService,
) {

    @Transactional(readOnly = true)
    fun getMyProfile(email: String): GetMemberResponse {
        val user = userService.getUser(email)

        return GetMemberResponse(profileService.getProfileByUser(user))
    }

    @Transactional(readOnly = true)
    fun getProfiles(email: String): List<GetMemberResponse> {
        val user = userService.getUser(email)

        val profile = profileService.getProfileByUser(user)
        val friendMaps = friendMapService.getFriendMaps(profile)
        val friends = profileService.getProfilesById(friendMaps.map { it.friendProfile.id })

        return friends.map { GetMemberResponse(it) }
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
