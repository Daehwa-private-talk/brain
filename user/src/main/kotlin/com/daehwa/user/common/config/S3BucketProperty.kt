package com.daehwa.user.common.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "cloud.aws.s3")
class S3BucketProperty {
    var bucket: String = ""
    var folder: String = ""

    fun getS3Destination() = "$bucket/$folder"
    fun getKey(fileName: String) = "$folder/$fileName"
}
