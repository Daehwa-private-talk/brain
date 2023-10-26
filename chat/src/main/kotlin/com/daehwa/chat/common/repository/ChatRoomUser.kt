package com.daehwa.chat.common.repository

import com.daehwa.user.common.jpa.base_entity.DateBaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ChatRoomUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val chatRoomId: Int,
    val userId: Int
) : DateBaseEntity()
