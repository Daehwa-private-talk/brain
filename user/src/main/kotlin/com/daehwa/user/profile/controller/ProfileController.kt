package com.daehwa.user.profile.controller

import com.daehwa.core.dto.SuccessResponse
import com.daehwa.core.model.AuthenticatedUser
import com.daehwa.user.profile.dto.CreateFriendsRequest
import com.daehwa.user.profile.dto.CreateProfileRequest
import com.daehwa.user.profile.facade_service.ProfileFacadeService
import com.daehwa.user.profile.service.ProfileService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/profiles")
class ProfileController(
    private val profileFacadeService: ProfileFacadeService,
    private val profileService: ProfileService,
) {
    @GetMapping("/me")
    fun getMyProfile(@AuthenticationPrincipal user: AuthenticatedUser) =
        SuccessResponse.of(profileFacadeService.getMyProfile(user.email))

    @GetMapping("/friends")
    fun getFriends(@AuthenticationPrincipal user: AuthenticatedUser) =
        SuccessResponse.of(profileFacadeService.getProfiles(user.email))

    @PostMapping("/me")
    fun createMyProfile(
        @AuthenticationPrincipal user: AuthenticatedUser,
        @RequestBody request: CreateProfileRequest,
    ): SuccessResponse<Unit> {
        profileService.createProfile(user.email, request)

        return SuccessResponse.DEFAULT
    }

    @PostMapping("/friends")
    fun createFriends(
        @AuthenticationPrincipal user: AuthenticatedUser,
        @RequestBody request: CreateFriendsRequest,
    ): SuccessResponse<Unit> {
        profileFacadeService.createFriends(user.email, request)

        return SuccessResponse.DEFAULT
    }
}
