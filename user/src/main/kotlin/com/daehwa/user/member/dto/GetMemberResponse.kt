package com.daehwa.user.member.dto

data class GetMemberResponse(
    val id: Int,
    val image: String,
    val name: String,
    val nickname: String?,
    val description: String?,
)
