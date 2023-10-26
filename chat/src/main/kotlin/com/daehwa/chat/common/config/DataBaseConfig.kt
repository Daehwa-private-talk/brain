package com.daehwa.chat.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@Configuration
class DataBaseConfig {
    @Bean
    fun loginUserAuditorAware(): AuditorAware<String> {
        return LoginUserAuditorAware()
    }
}
