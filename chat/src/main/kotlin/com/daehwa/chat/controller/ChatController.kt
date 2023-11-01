package com.daehwa.chat.controller

import com.daehwa.chat.model.ChatMessage
import com.daehwa.chat.model.ChatUrlUtils.SUBSCRIBE_URL
import com.daehwa.chat.service.ChatService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller


@Controller
class ChatController(
    private val chatService: ChatService,
) {
    @MessageMapping("$SUBSCRIBE_URL/chat/enter")
    fun enter(message: ChatMessage) = chatService.getSubscribe(message)
}
