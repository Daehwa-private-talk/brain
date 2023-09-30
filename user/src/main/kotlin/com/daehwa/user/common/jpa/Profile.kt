package com.daehwa.user.common.jpa

import com.daehwa.core.jpa.DaehwaUser
import com.daehwa.user.common.jpa.base_entity.BaseEntity
import jakarta.persistence.*

@Entity
class Profile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val name: String,
    val nickname: String?,
    val image: String?,
    val statusMessage: String?,
    val userId: Int,
    @OneToMany(mappedBy = "userProfile", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val friendMaps: List<FriendMap>,
    @OneToMany(mappedBy = "friendProfile", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val userMaps: List<FriendMap>,
) : BaseEntity()
