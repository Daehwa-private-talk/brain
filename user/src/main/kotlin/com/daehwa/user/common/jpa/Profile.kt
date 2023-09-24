package com.daehwa.user.common.jpa

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
    @OneToOne
    @JoinColumn(name = "user_id")
    val user: DaehwaUser,
    @OneToMany(mappedBy = "profile", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val friendMaps: List<FriendMap>
) : BaseEntity()
