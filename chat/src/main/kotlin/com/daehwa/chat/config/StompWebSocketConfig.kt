package com.daehwa.chat.config

import com.daehwa.chat.model.ChatUrlUtils.PUBLISH_URL
import com.daehwa.chat.model.ChatUrlUtils.SUBSCRIBE_URL
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

const val WEB_SOCKET_END_POINT = "/v1/api/socket"

@Configuration
@EnableWebSocketMessageBroker
class StompWebSocketConfig(
    @Value("\${spring.security.cors.allowOrigins}")
    private val allowOrigins: List<String>,
    private val chatErrorHandler: ChatErrorHandler,
    private val stompHandler: StompHandler,
) : WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.setErrorHandler(chatErrorHandler)
            .addEndpoint(WEB_SOCKET_END_POINT)
            .setAllowedOrigins(*allowOrigins.toTypedArray())
            .withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.setApplicationDestinationPrefixes(PUBLISH_URL)
        registry.enableSimpleBroker(SUBSCRIBE_URL)
    }

    override fun configureClientInboundChannel(registration: ChannelRegistration) {
        registration.interceptors(stompHandler)
    }
}
