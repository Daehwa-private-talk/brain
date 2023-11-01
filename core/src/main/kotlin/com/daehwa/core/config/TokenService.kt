package com.daehwa.core.config

import com.daehwa.core.config.JasyptConfig.Companion.JASYPT_ENCRYPTOR
import com.daehwa.core.jpa.AuthenticatedUser
import com.daehwa.core.jpa.DaehwaUser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.jasypt.encryption.StringEncryptor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenService(
    tokenProperty: TokenProperty,
    @Qualifier(JASYPT_ENCRYPTOR)
    private val jasypt: StringEncryptor,
) {
    private val signingKey = Keys.hmacShaKeyFor(tokenProperty.secretKey.toByteArray())

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }

    fun resolveAccessToken(request: HttpServletRequest): String? {
        val accessTokenCookie = request.cookies?.firstOrNull { it.name == "daehwa.access_token" }

        return accessTokenCookie?.value ?: request.getHeader(AUTHORIZATION_HEADER)
    }

    fun getRefreshToken(token: String): String {
        val nonce = Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body["nonce"]
            .toString()

        return jasypt.decrypt(nonce).split("*")[0]
    }

    fun isValidateToken(token: String?): Boolean {
        return try {
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .body

            isNotExpired(claims)
        } catch (e: Exception) {
            false
        }
    }

    private fun isNotExpired(claims: Claims): Boolean = claims.expiration.after(Date())

    fun getAuthentication(user: DaehwaUser): Authentication {
        val authenticatedUser = AuthenticatedUser(
            name = user.name,
            password = user.password,
            authorities = listOf(SimpleGrantedAuthority(user.role.getRoleName())),
            id = user.id,
            email = user.email,
        )

        return UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.authorities)
    }
}
