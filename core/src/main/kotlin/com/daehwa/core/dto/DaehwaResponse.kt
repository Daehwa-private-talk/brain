package com.daehwa.core.dto

import com.fasterxml.jackson.annotation.JsonIgnore

interface DaehwaResponse

open class BaseResponse<T>(
    val success: Boolean,
    val status: Status,
    val result: T?,
) : DaehwaResponse

data class Status(
    @JsonIgnore
    val responseCode: ResponseCode,
    val message: String = "",
) {
    companion object {
        val DEFAULT = Status(ResponseCode.OK)
    }

    val code: Int
        get() = responseCode.code
}
