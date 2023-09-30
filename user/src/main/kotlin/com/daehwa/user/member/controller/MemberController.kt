package com.daehwa.user.member.controller

import com.daehwa.core.dto.SuccessResponse
import com.daehwa.core.jpa.AuthenticatedUser
import com.daehwa.user.member.dto.GetMemberResponse
import com.daehwa.user.member.facade_service.MemberFacadeService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/members")
class MemberController(
    private val memberFacadeService: MemberFacadeService,
) {
    @GetMapping
    fun getMembers(@AuthenticationPrincipal user: AuthenticatedUser): SuccessResponse<List<GetMemberResponse>> =
        SuccessResponse.of(memberFacadeService.getMemberProfiles(user.id))
}
