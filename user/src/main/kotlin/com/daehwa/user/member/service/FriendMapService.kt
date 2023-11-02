package com.daehwa.user.member.service

import com.daehwa.user.common.repository.FriendMap
import com.daehwa.user.common.repository.FriendMapRepository
import com.daehwa.user.common.repository.Profile
import org.springframework.stereotype.Service

@Service
class FriendMapService(
    private val friendMapRepository: FriendMapRepository,
) {
    fun getFriendMaps(userProfile: Profile): List<FriendMap> = friendMapRepository.findAllByUserProfile(userProfile)
}
