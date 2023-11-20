package com.daehwa.core.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class AuthenticatedUser(
    name: String,
    password: String,
    authorities: List<GrantedAuthority> = emptyList(),
    val userId: Int,
    val email: String,
) : User(name, password, authorities)
