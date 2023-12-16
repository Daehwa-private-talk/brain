tasks {
    bootJar {
        enabled = true
    }

    jar {
        enabled = false
    }
}

dependencies {
    val flywayVersion = "9.15.2"
    val kotlinLoggingVersion = "3.0.5"
    val jwtVersion = "0.11.5"
    val springDocVersion = "2.1.0"

    implementation(project(":core"))
    testImplementation(project(":core"))

    // 기본 설정
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocVersion")

    // spring cloud
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    // 시큐리티
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-test")
    implementation("io.jsonwebtoken:jjwt-api:$jwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jwtVersion")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

    // DB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.mysql:mysql-connector-j")
    implementation("org.flywaydb:flyway-mysql:$flywayVersion")
    testImplementation("org.flywaydb:flyway-mysql:$flywayVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // aws
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
}
