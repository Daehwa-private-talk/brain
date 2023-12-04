package com.daehwa.user.common.config

import com.daehwa.core.config.JasyptConfig.Companion.JASYPT_ENCRYPTOR
import com.daehwa.core.config.TokenProperty
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

    fun createRefreshToken(): String =
        Jwts.builder()
            .setExpiration(Date(Date().time + tokenProperty.refreshTokenRenewHour))
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact()


    @Transactional
    fun createAccessToken(email: String, refreshToken: String): String {
        val nonce = jasypt.encrypt(refreshToken + "*" + LocalDateTime.now())
        val claims = getClaims(email, nonce)
        val now = Date()

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenProperty.accessTokenExpireTime))
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun getClaims(email: String, nonce: String) = mapOf(
        "email" to email,
        "nonce" to nonce,
    )
}
