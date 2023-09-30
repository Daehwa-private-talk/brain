package com.daehwa.user.common.jpa

import com.daehwa.core.jpa.DaehwaUser
import com.daehwa.user.common.jpa.base_entity.BaseEntity
import jakarta.persistence.*

@Entity
class FriendMap(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    val userProfile: Profile,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_profile_id")
    val friendProfile: Profile,
) : BaseEntity()
