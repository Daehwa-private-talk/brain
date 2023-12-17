package com.daehwa.user.common.repository

import com.daehwa.user.common.repository.base_entity.DateBaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Profile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val email: String,
    var nickname: String?,
    var imageUrl: String? = null,
    var imageFileName: String? = null,
    var emoji: String?,
    var statusMessage: String?,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: DaehwaUser,
) : DateBaseEntity() {
    fun getProfileName(): String = nickname ?: user.name
    fun getBirthDay(): LocalDate = user.birthDate
}
