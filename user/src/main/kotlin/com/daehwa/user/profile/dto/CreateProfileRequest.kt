package com.daehwa.user.profile.dto

data class CreateProfileRequest(
    val nickname: String?,
    val image: String?,
    val statusMessage: String?,
)
