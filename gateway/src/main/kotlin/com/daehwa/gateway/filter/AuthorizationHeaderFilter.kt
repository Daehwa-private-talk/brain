package com.daehwa.gateway.filter

import com.daehwa.gateway.model.exception.DaehwaException
import com.daehwa.gateway.model.exception.ErrorCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class AuthorizationHeaderFilter(
    @Value("\${token.secret-key}")
    val secretKey: String,
) : AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {
    companion object {
        private const val COOKIE_KEY = "daehwa.access_token"
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    data class Config(val name:String = "AuthorizationHeaderFilter")

    private val signingKey = Keys.hmacShaKeyFor(secretKey.toByteArray())

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request

            val token = resolveAccessToken(request)

            if (!validateToken(token)) {
                throw DaehwaException(ErrorCode.UNAUTHORIZED, "JWT 토큰이 유효하지 않습니다.")
            }

            if (isExpired(getClaims(token))) {
                throw DaehwaException(ErrorCode.EXPIRED, "JWT 토큰이 만료되었습니다.")
            }

            chain.filter(exchange).then()
        }
    }

    private fun resolveAccessToken(request: ServerHttpRequest): String? {
        val token = request.cookies[COOKIE_KEY]?.firstOrNull()
        return token?.value ?: request.headers[AUTHORIZATION_HEADER]?.firstOrNull()
    }

    private fun validateToken(token: String?): Boolean {
        return try {
            getClaims(token)

            return true
        } catch (e: Exception) {
            false
        }
    }

    private fun getClaims(token: String?) =
        Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body

    fun isExpired(claims: Claims): Boolean = claims.expiration.before(Date())
}
