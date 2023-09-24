package com.daehwa.user.common.jpa

import com.daehwa.user.common.jpa.base_entity.BaseEntity
import jakarta.persistence.*

@Entity
class FriendMap(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: DaehwaUser,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_id")
    val friend: DaehwaUser,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    val profile: Profile,
) : BaseEntity()
