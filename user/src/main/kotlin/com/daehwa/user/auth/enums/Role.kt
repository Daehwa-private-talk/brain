package com.daehwa.user.auth.enums

enum class Role {
    USER, ADMIN,;

    fun getRoleName() = "ROLE_${this.name}"
}
