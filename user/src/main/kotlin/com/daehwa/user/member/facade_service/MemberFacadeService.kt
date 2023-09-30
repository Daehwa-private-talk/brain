package com.daehwa.user.member.facade_service

import com.daehwa.user.member.dto.GetMemberResponse
import com.daehwa.user.member.service.FriendMapService
import com.daehwa.user.member.service.ProfileService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MemberFacadeService(
    private val profileService: ProfileService,
    private val friendMapService: FriendMapService,
) {
    @Transactional
    fun getMemberProfiles(userId: Int): List<GetMemberResponse> {
        /**
         * 1. 유저를 가져온다
         * 2. 친구 맵 목록을 가져온다
         * 3. 친구들 profile을 넘겨준다
         */
        val user = profileService.getProfile(userId)
        val friendMaps = friendMapService.getFriendMaps(user)
        val friends = profileService.getProfiles(friendMaps.map { it.friendProfile.id })

        return listOf(GetMemberResponse(user)) + friends.map { GetMemberResponse(it) }
    }
}
