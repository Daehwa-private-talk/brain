package com.daehwa.user.profile.dto

import com.daehwa.user.common.repository.Profile
import java.time.LocalDate

data class GetProfileResponse(
    val id: Int,
    val image: String?,
    val nickname: String,
    val statusMessage: String?,
    val birthDate: LocalDate?,
    val emoji: String?,
) {
    constructor(profile: Profile) : this(
        id = profile.id,
        image = profile.imageUrl,
        nickname = profile.getProfileName(),
        statusMessage = profile.statusMessage,
        birthDate = profile.getBirthDay(),
        emoji = profile.emoji
    )
}
