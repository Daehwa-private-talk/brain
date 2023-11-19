package com.daehwa.chatroom.repository

import com.daehwa.user.common.repository.base_entity.DateBaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ChatRoom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val createdBy: String,
) : DateBaseEntity()
