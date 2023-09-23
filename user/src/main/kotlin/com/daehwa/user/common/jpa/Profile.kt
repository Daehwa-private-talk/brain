package com.daehwa.user.common.jpa

import com.daehwa.user.common.jpa.base_entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Profile(
    @Id
    @GeneratedValue
    val id: Int = 0,
    val image: String?,
    val statusMessage: String?,
) : BaseEntity()
