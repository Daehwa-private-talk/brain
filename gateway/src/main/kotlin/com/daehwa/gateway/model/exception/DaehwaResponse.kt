package com.daehwa.gateway.model.exception

interface DaehwaResponse

data class Status(
    val responseCode: ResponseCode,
    val message: String = "",
)
