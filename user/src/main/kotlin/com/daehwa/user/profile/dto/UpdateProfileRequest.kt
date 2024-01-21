package com.daehwa.user.profile.dto

data class UpdateProfileRequest (
    val nickname: String?,
    val statusMessage: String?,
    val emoji: String?,
)
