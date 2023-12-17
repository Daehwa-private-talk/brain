package com.daehwa.user.profile.dto

data class CreateProfileRequest(
    val nickname: String?,
    val statusMessage: String?,
    val emoji: String?,
)
