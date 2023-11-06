package com.daehwa.user.auth.controller

import com.daehwa.user.auth.service.RedisTestService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/redis/tset")
class RedisTestController(
    private val service: RedisTestService,
) {
    @PostMapping
    fun save() = service.save()

    @GetMapping
    fun get() = service.get()
}
