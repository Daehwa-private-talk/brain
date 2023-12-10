package com.daehwa.user.auth.dto

import jakarta.validation.constraints.Email
import java.time.LocalDate

data class SignUpRequest(
    @field: Email(message = "이메일 형식이 잘못되었습니다.")
    val email: String,
    val password: String,
    val name: String,
    val birthDate: LocalDate,
)
