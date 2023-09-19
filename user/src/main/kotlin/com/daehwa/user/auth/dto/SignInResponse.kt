package com.daehwa.user.auth.dto

data class SignInResponse(
    val email: String,
    val token: TokenResponse
)
