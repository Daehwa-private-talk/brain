package com.daehwa.core.exception

class DaehwaException(
    val errorCode: ErrorCode,
    message: String? = null,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
