package com.daehwa.chat.common.config

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSockChatHandler: TextWebSocketHandler() {
    companion object {
        private val logger = KotlinLogging.logger { }
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        logger.info { "payload ${message.payload}" }
        val textMessage = TextMessage("Welcome chatting")
        session.sendMessage(textMessage)
    }
}
