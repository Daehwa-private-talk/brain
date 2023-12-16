package com.daehwa.user.profile.controller

import com.daehwa.core.dto.SuccessResponse
import com.daehwa.user.profile.service.ImageService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/v1/api/profile/images")
class ProfileImageController(
    private val imageService: ImageService,
) {
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun saveProfileImage(@RequestParam images: MultipartFile): SuccessResponse<String> {
        return SuccessResponse.of(imageService.save(images))
    }
}
