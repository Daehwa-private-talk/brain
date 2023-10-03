package com.daehwa.core.dto

class SuccessResponse<T : Any?>(status: Status, result: T) : BaseResponse<T>(true, status, result) {
    companion object {
        val DEFAULT = of(Unit)

        fun <T> of(result: T): SuccessResponse<T> {
            return SuccessResponse(Status(ResponseCode.OK, "ok"), result)
        }
    }
}
