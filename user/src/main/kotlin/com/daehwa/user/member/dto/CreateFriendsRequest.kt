package com.daehwa.user.member.dto

data class CreateFriendsRequest(
    val friendEmails: Set<String> = emptySet(),
)
