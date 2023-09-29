package com.daehwa.user.common.config

import com.daehwa.core.config.JasyptConfig.Companion.JASYPT_ENCRYPTOR
import com.daehwa.core.config.TokenProperty
import com.daehwa.core.jpa.DaehwaUser
import com.daehwa.core.utils.UUIDUtils
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.transaction.Transactional
import org.jasypt.encryption.StringEncryptor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class TokenProvider(
    private val tokenProperty: TokenProperty,
    @Qualifier(JASYPT_ENCRYPTOR)
    private val jasypt: StringEncryptor,
) {
    private val signingKey = Keys.hmacShaKeyFor(tokenProperty.secretKey.toByteArray())

    fun createRefreshToken(): String = UUIDUtils.generate()

    @Transactional
    fun createAccessToken(user: DaehwaUser, refreshToken: String): String {
        val nonce = jasypt.encrypt(refreshToken + "*" + LocalDateTime.now())
        val claims = getClaims(user, nonce)
        val now = Date()

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenProperty.accessTokenExpireTime))
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun getClaims(user: DaehwaUser, nonce: String) = mapOf(
        "name" to user.name,
        "password" to user.password,
        "authorities" to user.role,
        "id" to user.id,
        "email" to user.email,
        "nonce" to nonce,
    )
}
