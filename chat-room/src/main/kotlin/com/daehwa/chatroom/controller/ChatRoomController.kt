package com.daehwa.chatroom.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/")
class ChatRoomController {
    /**
     * 1. 채팅방 전체 조회
     * 2. 채팅방 상세 조회
     * 3. 채팅방 생성
     */
    @GetMapping("test")
    fun test() = "ter"
}
