package com.daehwa.chat.common.config

import com.daehwa.chat.model.ChatUrlUtils.PUBLISH_URL
import com.daehwa.chat.model.ChatUrlUtils.SUBSCRIBE_URL
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

const val WEB_SOCKET_END_POINT = "/v1/api/socket/chat"

@Configuration
@EnableWebSocketMessageBroker
class StompWebSocketConfig(
    @Value("\${spring.security.cors.allowOrigins}")
    private val allowOrigins: List<String>,
) : WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint(WEB_SOCKET_END_POINT)
            .setAllowedOrigins("*")
            .withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.setApplicationDestinationPrefixes(PUBLISH_URL)
        registry.enableSimpleBroker(SUBSCRIBE_URL)
    }
}
