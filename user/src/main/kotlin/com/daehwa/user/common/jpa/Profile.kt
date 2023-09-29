package com.daehwa.user.common.jpa

import com.daehwa.core.jpa.DaehwaUser
import com.daehwa.user.common.jpa.base_entity.BaseEntity
import jakarta.persistence.*

@Entity
class Profile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val nickname: String?,
    val image: String?,
    val statusMessage: String?,
    val userId: Int,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val friendMaps: List<FriendMap>,
    @OneToMany(mappedBy = "friend", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val userMaps: List<FriendMap>,
) : BaseEntity()
