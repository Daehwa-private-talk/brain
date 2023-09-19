package com.daehwa.user.member.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/members")
class MemberController {
    @GetMapping
    fun getMembers(): String {
        return "hi"
    }
}
