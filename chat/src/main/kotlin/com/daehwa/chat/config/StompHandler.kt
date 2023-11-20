package com.daehwa.chat.config

import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.stereotype.Component

@Component
class StompHandler(private val jwtUtils: JwtUtils) : ChannelInterceptor {
    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        val accessor = StompHeaderAccessor.wrap(message)

        if (StompCommand.CONNECT == accessor.command) {
            val header = jwtUtils.extractJwt(accessor)
            jwtUtils.validateToken(header)
        }

        return message
    }
}
