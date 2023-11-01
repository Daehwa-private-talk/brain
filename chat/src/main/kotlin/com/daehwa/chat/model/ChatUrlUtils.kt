package com.daehwa.chat.model

object ChatUrlUtils {
    const val SUBSCRIBE_URL = "/sub"
    const val PUBLISH_URL = "/pub"

    fun getSubscribeUrl(resource: String): String {
        return SUBSCRIBE_URL + resource
    }
}
