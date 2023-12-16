package com.daehwa.user.profile.dto

import org.springframework.web.multipart.MultipartFile

data class SaveProfileImageRequest(
    val images: List<MultipartFile>
)
