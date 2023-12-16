package com.daehwa.user.common.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "cloud.aws.credentials")
class S3CredentialsProperty {
    var accessKey: String = ""
    var secretKey: String = ""
}
