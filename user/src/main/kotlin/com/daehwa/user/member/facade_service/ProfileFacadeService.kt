package com.daehwa.user.member.facade_service

import com.daehwa.user.member.dto.GetMemberResponse
import com.daehwa.user.member.service.FriendMapService
import com.daehwa.user.member.service.ProfileService

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileFacadeService(
    private val profileService: ProfileService,
    private val friendMapService: FriendMapService,
) {
    @Transactional(readOnly = true)
    fun getProfiles(userId: Int): List<GetMemberResponse> {
        val profile = profileService.getProfile(userId)
        val friendMaps = friendMapService.getFriendMaps(profile)
        val friends = profileService.getProfiles(friendMaps.map { it.friendProfile.id })

        return listOf(GetMemberResponse(profile)) + friends.map { GetMemberResponse(it) }
    }
}
