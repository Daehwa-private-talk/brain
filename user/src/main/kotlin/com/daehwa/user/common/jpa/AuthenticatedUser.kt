package com.daehwa.user.common.jpa

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class AuthenticatedUser(
    name: String,
    password: String,
    authorities: List<GrantedAuthority> = emptyList(),
    val id: Int,
    val email: String,
) : User(name, password, authorities)