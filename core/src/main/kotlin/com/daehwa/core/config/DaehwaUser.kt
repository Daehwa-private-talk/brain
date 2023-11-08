package com.daehwa.core.config

import com.daehwa.core.enums.Role
import com.daehwa.user.common.repository.base_entity.DateBaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.Where

@Entity
@Where(clause = "is_deleted = false")
class DaehwaUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val email: String,
    val password: String,
    val name: String,
    @Enumerated(EnumType.STRING)
    val role: Role = Role.USER,
    @Column(name = "is_enabled", columnDefinition = "TINYINT")
    val enabled: Boolean = true,
    @Column(name = "is_deleted", columnDefinition = "TINYINT")
    val deleted: Boolean = false,
) : DateBaseEntity()
