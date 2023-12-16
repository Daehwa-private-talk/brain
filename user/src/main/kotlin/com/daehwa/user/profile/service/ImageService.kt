package com.daehwa.user.profile.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.daehwa.core.exception.DaehwaException
import com.daehwa.core.exception.ErrorCode
import com.daehwa.user.common.config.S3BucketProperty
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageService(
    private val s3Client: AmazonS3,
    private val bucketProperty: S3BucketProperty,
) {
    @Transactional
    fun save(image: MultipartFile): String {
        return uploadS3(image)
    }

    private fun uploadS3(multipartFile: MultipartFile): String {
        val filename = multipartFile.originalFilename ?: throw DaehwaException(ErrorCode.NOT_FOUND, "파일 이름이 존재하지 않습니다.")

        val metadata = ObjectMetadata()
        metadata.contentType = multipartFile.contentType
        metadata.contentLength = multipartFile.inputStream.available().toLong()

        val request = PutObjectRequest(bucketProperty.getS3Destination(), filename, multipartFile.inputStream, metadata)

        s3Client.putObject(request)

        return createPreSignedUrl(filename)
    }

    fun createPreSignedUrl(filename: String): String {
        val request = GeneratePresignedUrlRequest(bucketProperty.bucket, "${bucketProperty.folder}/$filename")

        return s3Client.generatePresignedUrl(request).toString()
    }
}
