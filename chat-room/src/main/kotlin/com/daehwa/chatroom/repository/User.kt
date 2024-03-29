package com.daehwa.chatroom.repository

import com.daehwa.user.common.repository.base_entity.DateBaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val name: String,
    val nickname: String,
    val email: String,
) : DateBaseEntity()
