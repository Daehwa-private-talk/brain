package com.daehwa.user.member.controller

import com.daehwa.core.dto.SuccessResponse
import com.daehwa.core.jpa.AuthenticatedUser
import com.daehwa.user.member.dto.CreateProfileRequest
import com.daehwa.user.member.dto.GetMemberResponse
import com.daehwa.user.member.facade_service.ProfileFacadeService
import com.daehwa.user.member.service.ProfileService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/profiles")
class ProfileController(
    private val profileFacadeService: ProfileFacadeService,
    private val profileService: ProfileService,
) {
    @GetMapping
    fun getProfiles(@AuthenticationPrincipal user: AuthenticatedUser): SuccessResponse<List<GetMemberResponse>> =
        SuccessResponse.of(profileFacadeService.getProfiles(user.id))

    @PostMapping
    fun createProfile(
        @AuthenticationPrincipal user: AuthenticatedUser,
        @RequestBody request: CreateProfileRequest,
    ): SuccessResponse<Unit> {
        profileService.createProfile(user.id, request)

        return SuccessResponse.DEFAULT
    }
}
