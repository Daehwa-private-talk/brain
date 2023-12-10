package com.daehwa.user.member.service

import com.daehwa.core.exception.DaehwaException
import com.daehwa.core.exception.ErrorCode
import com.daehwa.user.common.repository.DaehwaUser
import com.daehwa.user.common.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    @Transactional(readOnly = true)
    fun getUser(email: String): DaehwaUser =
        userRepository.findByEmail(email) ?: throw DaehwaException(
            ErrorCode.NOT_FOUND, "유저가 존재하지 않습니다. email: $email",
        )

    @Transactional(readOnly = true)
    fun getUsers(emails: List<String>): List<DaehwaUser> =
        userRepository.findByEmailIn(emails)
}
