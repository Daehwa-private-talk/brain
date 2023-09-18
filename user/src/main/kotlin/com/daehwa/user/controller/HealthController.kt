package com.daehwa.user.controller

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE], value = ["/v1/api"])
@Hidden
class HealthController {
    @GetMapping("/health")
    fun getHealth(): ResponseEntity<Boolean> {
        return ResponseEntity.ok(true)
    }
}
