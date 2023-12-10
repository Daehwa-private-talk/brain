package com.daehwa.user.common.repository

import com.daehwa.user.common.repository.base_entity.DateBaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

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
) : DateBaseEntity()
