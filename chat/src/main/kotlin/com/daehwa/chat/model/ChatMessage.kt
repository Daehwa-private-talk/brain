package com.daehwa.chat.model

data class ChatMessage(
    val roomId: String,
    val sender: String,
    val message: String,
    val type: MessageType
)
