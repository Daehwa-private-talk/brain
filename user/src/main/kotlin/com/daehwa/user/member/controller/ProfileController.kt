package com.daehwa.user.member.controller

import com.daehwa.core.dto.SuccessResponse
import com.daehwa.core.model.AuthenticatedUser
import com.daehwa.user.member.dto.CreateProfileRequest
import com.daehwa.user.member.dto.GetMemberResponse
import com.daehwa.user.member.facade_service.ProfileFacadeService
import com.daehwa.user.member.service.ProfileService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/profiles")
class ProfileController(
    private val profileFacadeService: ProfileFacadeService,
    private val profileService: ProfileService,
) {
    @GetMapping
    fun getProfiles(@AuthenticationPrincipal user: AuthenticatedUser): SuccessResponse<List<GetMemberResponse>> =
        SuccessResponse.of(profileFacadeService.getProfiles(user.userId))

    @PostMapping
    fun createProfile(
        @AuthenticationPrincipal user: AuthenticatedUser,
        @RequestBody request: CreateProfileRequest,
    ): SuccessResponse<Unit> {
        profileService.createProfile(user.userId, request)

        return SuccessResponse.DEFAULT
    }
}
