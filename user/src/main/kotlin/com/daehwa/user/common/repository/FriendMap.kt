package com.daehwa.user.common.repository

import com.daehwa.user.common.repository.base_entity.DateBaseEntity
import jakarta.persistence.*

@Entity
class FriendMap(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_profile_id")
    val userProfile: Profile,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_profile_id")
    val friendProfile: Profile,
) : DateBaseEntity()
