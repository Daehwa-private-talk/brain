package com.daehwa.gateway.model.exception

import org.springframework.http.HttpStatus

enum class ResponseCode(val code: Int, val httpStatus: HttpStatus) {
    UNAUTHORIZED(4001, HttpStatus.UNAUTHORIZED),
    NOT_FOUND(4004, HttpStatus.NOT_FOUND),
    EXPIRED(4011, HttpStatus.UNAUTHORIZED)

}
