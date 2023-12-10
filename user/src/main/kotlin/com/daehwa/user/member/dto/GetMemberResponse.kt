package com.daehwa.user.member.dto

import com.daehwa.user.common.repository.Profile
import java.time.LocalDate

data class GetMemberResponse(
    val id: Int,
    val image: String,
    val nickname: String?,
    val statusMessage: String?,
    val birthDate: LocalDate?,
) {
    constructor(profile: Profile) : this(
        profile.id, "not yet prepared", profile.nickname, profile.statusMessage, profile.user.birthDate,
    )
}
