package com.daehwa.chat.config

import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler

@Component
class ChatErrorHandler : StompSubProtocolErrorHandler() {

}
