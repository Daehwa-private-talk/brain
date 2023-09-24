package com.daehwa.user.common.jpa

import com.daehwa.user.auth.enums.Role
import com.daehwa.user.common.jpa.base_entity.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.Where
import java.time.LocalDateTime

@Entity
@Where(clause = "is_deleted = false")
class DaehwaUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val email: String,
    val password: String,
    val name: String,
    var refreshToken: String? = null,
    var refreshTokenExpiredAt: LocalDateTime? = null,
    var signInAt: LocalDateTime? = null,
    @Enumerated(EnumType.STRING)
    val role: Role = Role.USER,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val userMap: List<FriendMap> = emptyList(),
    @OneToMany(mappedBy = "friend", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val friendMap: List<FriendMap> = emptyList(),
    @OneToOne(mappedBy = "user", cascade = [CascadeType.REMOVE])
    val profile: Profile? = null,
    @Column(name = "is_enabled", columnDefinition = "TINYINT")
    val enabled: Boolean = true,
    @Column(name = "is_deleted", columnDefinition = "TINYINT")
    val deleted: Boolean = false,
) : BaseEntity() {
    fun updateRefreshToken(
        refreshToken: String,
        refreshTokenExpiredAt: LocalDateTime,
    ) {
        this.refreshToken = refreshToken
        this.refreshTokenExpiredAt = refreshTokenExpiredAt
    }

    fun updateSignInAt(signInAt: LocalDateTime) {
        this.signInAt = signInAt
    }
}
