package com.daehwa.user.member.service

import com.daehwa.user.common.repository.FriendMap
import com.daehwa.user.common.repository.FriendMapRepository
import com.daehwa.user.common.repository.Profile
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FriendMapService(
    private val friendMapRepository: FriendMapRepository,
) {
    @Transactional(readOnly = true)
    fun getFriendMaps(userProfile: Profile): List<FriendMap> = friendMapRepository.findAllByUserProfile(userProfile)

    @Transactional
    fun createFriendMaps(userProfile: Profile, friendProfiles: List<Profile>) {
        val friendMaps = friendProfiles.map {
            FriendMap(userProfile = userProfile, friendProfile = it)
        }

        friendMapRepository.saveAll(friendMaps)
    }
}
