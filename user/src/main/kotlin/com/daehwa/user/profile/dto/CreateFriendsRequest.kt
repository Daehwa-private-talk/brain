package com.daehwa.user.profile.dto

data class CreateFriendsRequest(
    val friendEmails: Set<String> = emptySet(),
)
