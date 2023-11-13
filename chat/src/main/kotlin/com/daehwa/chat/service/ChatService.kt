package com.daehwa.chat.service

import com.daehwa.chat.model.ChatMessage
import com.daehwa.chat.model.ChatUrlUtils
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

private const val CHAT_ROOM_URL = "/chat/rooms/"

@Service
class ChatService(
    private val template: SimpMessageSendingOperations,
) {
    fun getSubscribe(message: ChatMessage) {
        val subscribeUrl = ChatUrlUtils.getSubscribeUrl(CHAT_ROOM_URL + message.roomId)

        template.convertAndSend(subscribeUrl, message)
    }
}
