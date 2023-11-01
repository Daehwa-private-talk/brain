package com.daehwa.core.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.daehwa.core.*"])
class UserDataBaseConfig {

}
