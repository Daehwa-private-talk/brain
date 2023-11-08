package com.daehwa.core.config

import com.daehwa.core.exception.DaehwaException
import com.daehwa.core.exception.ErrorCode
import com.daehwa.core.jpa.LoginUser
import com.daehwa.core.jpa.LoginUserRepository
import com.daehwa.core.utils.CookieUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.time.LocalDateTime

@Component
class JwtAuthenticationFilter(
    private val tokenService: TokenService,
    private val loginUserRepository: LoginUserRepository,
) : OncePerRequestFilter() {
    companion object {
        private const val COOKIE_KEY = "daehwa.access_token"
        private const val ACCESS_TOKEN_EXPIRE_HOUR = 2L
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token: String? = tokenService.resolveAccessToken(request)

        if (validateToken(token)) {
            val refreshToken = tokenService.getRefreshToken(token!!)

            val user = loginUserRepository.findByRefreshToken(refreshToken) ?: kotlin.run {
                expireAccessToken(request, response)
                throw DaehwaException(ErrorCode.DUPLICATED_LOGIN, "중복 로그인 되었습니다.")
            }

            validateExpireTime(user.signInAt)
            setAuthentication(user)
        }

        filterChain.doFilter(request, response)
    }

    private fun validateToken(token: String?) = tokenService.isValidateToken(token)
    private fun validateExpireTime(signInAt: LocalDateTime) {
        if (signInAt.plusHours(ACCESS_TOKEN_EXPIRE_HOUR).isBefore(LocalDateTime.now())) {
            throw DaehwaException(ErrorCode.EXPIRED, "만료된 access token 입니다.")
        }
    }

    private fun expireAccessToken(request: HttpServletRequest, response: HttpServletResponse) {
        request.cookies?.firstOrNull { it.name == COOKIE_KEY }?.let {
            CookieUtils.addCookie(
                response = response,
                key = COOKIE_KEY,
                value = "",
                path = "/",
                minuteMaxAge = 0,
                httpOnly = true,
                secured = true,
            )
        }
    }

    private fun setAuthentication(user: LoginUser) {
        val authentication = tokenService.getAuthentication(user)
        SecurityContextHolder.getContext().authentication = authentication
    }
}
