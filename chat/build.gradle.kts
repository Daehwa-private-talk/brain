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

    implementation(project(":core"))
    testImplementation(project(":core"))

    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.flywaydb:flyway-mysql:$flywayVersion")
    testImplementation("org.flywaydb:flyway-mysql:$flywayVersion")
}

