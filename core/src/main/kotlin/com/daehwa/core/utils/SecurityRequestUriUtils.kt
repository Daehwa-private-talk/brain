package com.daehwa.core.utils

object SecurityRequestUriUtils {
    private val GLOBAL_ALLOWED_URIS = listOf(
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/swagger-resources/**",
        "/api-docs/swagger-config",
        "/api-docs",
        "/v1/api/auth/sign-up",
        "/v1/api/auth/sign-in",
        "/v1/api/auth/token/refresh",
        "/v1/api/health",
        "/v1/api/socket/**"
    )

    fun getGlobalAllowedUris(): List<String> {
        return GLOBAL_ALLOWED_URIS
    }
}
