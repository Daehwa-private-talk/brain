package com.daehwa.user.common.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config(
    private val property: S3CredentialsProperty,
    @Value("\${cloud.aws.region.static:ap-northeast-2}")
    private val region: String,
) {
    @Bean
    fun awsS3Client(): AmazonS3 {
        val credentials = BasicAWSCredentials(property.accessKey, property.secretKey)

        return AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            .withRegion(region)
            .build()
    }
}
