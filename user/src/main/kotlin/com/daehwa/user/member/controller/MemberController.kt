package com.daehwa.user.member.controller

import com.daehwa.user.common.jpa.AuthenticatedUser
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/members")
class MemberController {
    @GetMapping
    fun getMembers(@AuthenticationPrincipal user: AuthenticatedUser): String {

        return "hi"
    }
}
