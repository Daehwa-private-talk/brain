package com.daehwa.chat.controller

import com.daehwa.chat.model.ChatMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class ChatController() {
    @MessageMapping("/chat/enter")
    fun enter(message: ChatMessage) {

    }
}
