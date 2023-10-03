package com.daehwa.user.member.dto

import com.daehwa.user.common.jpa.Profile

data class GetMemberResponse(
    val id: Int,
    val image: String,
    val nickname: String?,
    val statusMessage: String?,
) {
    constructor(profile: Profile) : this(
        profile.id, "not yet prepared", profile.nickname, profile.statusMessage,
    )
}
