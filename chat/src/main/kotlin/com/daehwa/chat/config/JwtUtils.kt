package com.daehwa.chat.config

import com.daehwa.core.exception.DaehwaException
import com.daehwa.core.exception.ErrorCode
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component

@Component
class JwtUtils(
    @Value("\${token.secret-key}")
    secretKey: String,
) {
    private val signingKey = Keys.hmacShaKeyFor(secretKey.toByteArray())
    fun extractJwt(accessor: StompHeaderAccessor): String {
        return accessor.getFirstNativeHeader("Authorization") ?: throw DaehwaException(
            ErrorCode.FORBIDDEN,
            "인증되지 않은 소켓 연결입니다.",
        )
    }

    fun validateToken(token: String) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
        } catch (e: Exception) {
            throw
        }
    }
}
