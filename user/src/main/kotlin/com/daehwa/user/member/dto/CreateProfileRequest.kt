package com.daehwa.user.member.dto

data class CreateProfileRequest(
    val nickname: String?,
    val image: String?,
    val statusMessage: String?,
)
