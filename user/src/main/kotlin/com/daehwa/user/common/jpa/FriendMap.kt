package com.daehwa.user.common.jpa

import com.daehwa.user.common.jpa.base_entity.BaseEntity
import jakarta.persistence.*

@Entity
class FriendMap(
    @Id
    @GeneratedValue
    val id: Int = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: DaehwaUser,
    @ManyToOne
    @JoinColumn(name = "friend_id")
    val friend: DaehwaUser,
): BaseEntity()
