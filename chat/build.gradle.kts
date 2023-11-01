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
    val flywayVersion = "9.15.2"
    val jwtVersion = "0.11.5"
    implementation(project(":core"))
    testImplementation(project(":core"))

    // 기본 설정
    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // spring cloud
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    // DB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.mysql:mysql-connector-j")
    implementation("org.flywaydb:flyway-mysql:$flywayVersion")
    testImplementation("org.flywaydb:flyway-mysql:$flywayVersion")

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

