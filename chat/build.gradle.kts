tasks {
    bootJar {
        enabled = true
    }

    jar {
        enabled = false
    }
}

dependencies {
    val kotlinLoggingVersion = "3.0.5"
    val jwtVersion = "0.11.5"
    implementation(project(":core"))
    testImplementation(project(":core"))

    // 기본 설정
    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")

    // spring cloud
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    // 시큐리티
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-test")
    implementation("io.jsonwebtoken:jjwt-api:$jwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jwtVersion")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

    // socket
    implementation("org.springframework.boot:spring-boot-starter-websocket")
}

