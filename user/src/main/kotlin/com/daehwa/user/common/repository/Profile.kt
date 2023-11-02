package com.daehwa.user.common.repository

import com.daehwa.user.common.repository.base_entity.DateBaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Profile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val nickname: String?,
    val image: String?,
    val statusMessage: String?,
    val userId: Int,
    @OneToMany(mappedBy = "userProfile", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val friendMaps: List<FriendMap> = emptyList(),
    @OneToMany(mappedBy = "friendProfile", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val userMaps: List<FriendMap> = emptyList(),
) : DateBaseEntity()
