package com.daehwa.chatroom.config

import com.daehwa.core.jpa.AuthenticatedUser
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

class LoginUserAuditorAware : AuditorAware<String> {
    companion object {
        const val ANONYMOUS_USER = "anonymousUser"
    }

    override fun getCurrentAuditor(): Optional<String> {
        val authentication = SecurityContextHolder.getContext().authentication
            ?: return Optional.of(ANONYMOUS_USER)

        val user = authentication.principal as AuthenticatedUser

        return Optional.of(user.username)
    }
}
