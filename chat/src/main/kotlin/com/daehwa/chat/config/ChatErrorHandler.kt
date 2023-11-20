package com.daehwa.chat.config

import com.daehwa.core.exception.DaehwaException
import org.springframework.messaging.Message
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler

@Component
class ChatErrorHandler : StompSubProtocolErrorHandler() {
    override fun handleClientMessageProcessingError(
        clientMessage: Message<ByteArray>?,
        ex: Throwable,
    ): Message<ByteArray>? {
        if (ex is DaehwaException) {
            prepareDaehwaErrorMessage(ex)
        }

        return super.handleClientMessageProcessingError(clientMessage, ex)
    }

    private fun prepareDaehwaErrorMessage(ex: DaehwaException): Message<ByteArray> {
        val accessor = StompHeaderAccessor.create(StompCommand.ERROR)
        val code = ex.errorCode.toString()

        accessor.message = code
        accessor.setLeaveMutable(true)

        return MessageBuilder.createMessage(code.toByteArray(Charsets.UTF_8), accessor.messageHeaders)
    }
}
