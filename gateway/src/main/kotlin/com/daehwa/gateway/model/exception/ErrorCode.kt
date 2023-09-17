package com.daehwa.gateway.model.exception

import org.springframework.boot.logging.LogLevel

enum class ErrorCode(val responseCode: ResponseCode, val logLevel: LogLevel) {
    NOT_FOUND(ResponseCode.NOT_FOUND, LogLevel.ERROR),
    UNAUTHORIZED(ResponseCode.UNAUTHORIZED, LogLevel.WARN),
    EXPIRED(ResponseCode.EXPIRED, LogLevel.WARN)
}
