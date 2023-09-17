package com.daehwa.gateway.model.exception

class DaehwaException(
    val errorCode: ErrorCode,
    message: String?,
    cause: Throwable? = null
): RuntimeException(message, cause)
