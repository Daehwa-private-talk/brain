package com.daehwa.user.member.controller

import com.daehwa.core.jpa.AuthenticatedUser
import com.daehwa.user.member.service.MemberService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/members")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping
    fun getMembers(@AuthenticationPrincipal user: AuthenticatedUser): String {

        return "hi"
    }
}
