package com.daehwa.user.common.repository

import org.springframework.data.repository.CrudRepository

interface LoginUserRepository : CrudRepository<LoginUser, String>
