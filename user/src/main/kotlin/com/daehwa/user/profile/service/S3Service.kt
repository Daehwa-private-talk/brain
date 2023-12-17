package com.daehwa.user.profile.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.DeleteObjectRequest
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
class S3Service(
    private val s3Client: AmazonS3,
    private val bucketProperty: S3BucketProperty,
) {
    @Transactional
    fun save(file: MultipartFile): String {
        uploadS3(file)

        val filename = file.originalFilename ?: throw DaehwaException(ErrorCode.NOT_FOUND, "파일 이름이 존재하지 않습니다.")
        return createPreSignedUrl(filename)
    }

    private fun uploadS3(file: MultipartFile) {
        val filename = file.originalFilename ?: throw DaehwaException(ErrorCode.NOT_FOUND, "파일 이름이 존재하지 않습니다.")

        val metadata = ObjectMetadata()
        metadata.contentType = file.contentType
        metadata.contentLength = file.inputStream.available().toLong()

        val request = PutObjectRequest(bucketProperty.getS3Destination(), filename, file.inputStream, metadata)
        try {
            s3Client.putObject(request)
        } catch (ex: Exception) {
            throw DaehwaException(
                ErrorCode.INVALID_PARAMETER,
                "S3 업로드 작업이 실패했습니다. destination: ${bucketProperty.getS3Destination()}",
            )
        }
    }

    fun createPreSignedUrl(filename: String): String {
        val request = GeneratePresignedUrlRequest(bucketProperty.bucket, bucketProperty.getKey(filename))

        return s3Client.generatePresignedUrl(request).toString()
    }

    @Transactional
    fun delete(fileName: String) {
        val request = DeleteObjectRequest(bucketProperty.bucket, bucketProperty.getKey(fileName))

        if (checkExists(fileName)) {
            try {
                s3Client.deleteObject(request)
            } catch (e: Exception) {
                throw DaehwaException(ErrorCode.INVALID_PARAMETER, "S3 객체를 삭제하는데 실패했습니다. file name: $fileName")
            }
        }
    }

    private fun checkExists(fileName: String) =
        s3Client.doesObjectExist(bucketProperty.bucket, bucketProperty.getKey(fileName))
}
