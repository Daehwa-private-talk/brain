package com.daehwa.user.common.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository: JpaRepository<Profile, Int> {
}
