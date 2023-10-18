package com.daehwa.chat.common.repository

import com.daehwa.chat.model.ChatMessage
import com.daehwa.chat.model.MessageType
import com.daehwa.chat.service.ChatService
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.web.socket.WebSocketSession

@Entity
class ChatRoom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val sessions: MutableSet<WebSocketSession>,
) {

    fun handleActions(session: WebSocketSession, chatMessage: ChatMessage, chatService: ChatService) {
        if (chatMessage.type == MessageType.ENTER) {
            sessions.add(session)
            chatMessage.message
        }
    }

    fun <T> sendMessage(message: T, chatService: ChatService) {
        sessions.parallelStream().forEach {
            chatService.sendMessage(it, message)
        }
    }
}
