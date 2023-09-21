import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.8.22"
    val springVersion = "3.1.3"

    id("org.springframework.boot") version springVersion
    id("io.spring.dependency-management") version "1.1.3"
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.noarg") version kotlinVersion

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
    group = "com.daehwa"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        maxHeapSize = "4096m"

        useJUnitPlatform()
    }

    repositories {
        mavenCentral()
    }

}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
    apply(plugin = "org.jetbrains.kotlin.plugin.noarg")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    allOpen {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.MappedSuperclass")
    }

    noArg {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.MappedSuperclass")
    }


    dependencies {
        val flywayVersion = "9.15.2"
        val springDocVersion = "2.1.0"
        val kotlinLoggingVersion = "3.0.5"
        val jwtVersion = "0.11.5"

        // 기본 설정
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        // spring cloud
        implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

        // 시큐리티
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.security:spring-security-test")
        implementation("io.jsonwebtoken:jjwt-api:$jwtVersion")
        runtimeOnly("io.jsonwebtoken:jjwt-impl:$jwtVersion")
        runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jwtVersion")
        implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

        // spring doc
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocVersion")

        // DB
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("com.mysql:mysql-connector-j")
        implementation("org.flywaydb:flyway-mysql:$flywayVersion")
        testImplementation("org.flywaydb:flyway-mysql:$flywayVersion")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    val springCloudVersion = "2022.0.4"

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
