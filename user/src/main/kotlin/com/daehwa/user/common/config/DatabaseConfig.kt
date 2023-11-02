package com.daehwa.user.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.daehwa.core.*", "com.daehwa.user.*"])
@EnableJpaAuditing
class DatabaseConfig {
    @Bean
    fun loginUserAuditorAware(): AuditorAware<String> {
        return LoginUserAuditorAware()
    }
}
