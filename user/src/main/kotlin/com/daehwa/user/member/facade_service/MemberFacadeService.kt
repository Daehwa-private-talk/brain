package com.daehwa.user.member.facade_service

import com.daehwa.user.member.service.MemberService
import org.springframework.stereotype.Service

@Service
class MemberFacadeService(
    private val memberService: MemberService
) {
}
