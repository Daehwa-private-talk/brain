package com.daehwa.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jasypt")
class JasyptProperty {
    var encryptKey: String = ""
    var algorithm: String = ""
}
