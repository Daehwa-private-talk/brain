package com.daehwa.chat.model

object ChatUrlUtils {
    private const val SUBSCRIBE_URL = "/sub"

    fun getSubscribeUrl(resource: String): String {
        return SUBSCRIBE_URL + resource
    }
}
