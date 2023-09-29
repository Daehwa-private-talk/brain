package com.daehwa.core.utils

import java.util.*

object UUIDUtils {
    fun generate(): String = UUID.randomUUID().toString()
}
