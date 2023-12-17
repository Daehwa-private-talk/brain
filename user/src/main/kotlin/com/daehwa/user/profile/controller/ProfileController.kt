package com.daehwa.user.profile.controller

import com.daehwa.core.dto.SuccessResponse
import com.daehwa.core.model.AuthenticatedUser
import com.daehwa.user.profile.dto.CreateFriendsRequest
import com.daehwa.user.profile.dto.CreateProfileRequest
import com.daehwa.user.profile.dto.UpdateProfileRequest
import com.daehwa.user.profile.facade_service.ProfileFacadeService
import com.daehwa.user.profile.service.ProfileService
import com.daehwa.user.profile.service.S3Service
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/v1/api/profiles")
class ProfileController(
    private val profileFacadeService: ProfileFacadeService,
    private val profileService: ProfileService,
    private val s3Service: S3Service,
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

    @PutMapping("/me/profile")
    fun updateMyProfileImage(
        @AuthenticationPrincipal user: AuthenticatedUser,
        @RequestBody request: UpdateProfileRequest,
    ): SuccessResponse<Unit> {
        profileService.updateProfile(user.email, request)

        return SuccessResponse.DEFAULT
    }

    @PostMapping("/image", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun saveProfileImage(
        @AuthenticationPrincipal user: AuthenticatedUser,
        @RequestParam image: MultipartFile,
    ): SuccessResponse<Unit> {
        profileFacadeService.uploadProfileImage(user.email, image)
        s3Service.save(image)

        return SuccessResponse.DEFAULT
    }

    @PutMapping("/image", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun updateProfileImage(
        @AuthenticationPrincipal user: AuthenticatedUser,
        @RequestParam image: MultipartFile,
    ): SuccessResponse<Unit> {
        profileFacadeService.updateProfileImage(user.email, image)

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
