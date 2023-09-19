package com.daehwa.user.common.utils

import java.util.UUID

object UUIDUtils {
    fun generate(): String = UUID.randomUUID().toString()
}
