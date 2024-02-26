import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
        id("application")
        id("checkstyle")
        id("jacoco")
        id("org.springframework.boot") version "3.2.2"
        id("io.spring.dependency-management") version "1.1.4"
        id("io.freefair.lombok") version "8.4"
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"

application {
        mainClass.set("hexlet.code.AppApplication")
}

java {
        sourceCompatibility = JavaVersion.VERSION_20
}

checkstyle {
    configFile = file("config/checkstyle/checkstyle.xml")
    toolVersion = "10.13.0"    // your choice here
}

configurations {
        compileOnly {
                extendsFrom(configurations.annotationProcessor.get())
        }
}

repositories {
        mavenCentral()
}

dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-data-rest")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-devtools")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
        implementation("org.mapstruct:mapstruct:1.5.5.Final")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
        implementation("org.openapitools:jackson-databind-nullable:0.2.6")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        runtimeOnly("com.h2database:h2")
        runtimeOnly("org.postgresql:postgresql")

        implementation("net.datafaker:datafaker:2.0.2")
        implementation("org.instancio:instancio-junit:3.3.1")
        testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")

        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
        testImplementation("org.jacoco:org.jacoco.core:0.8.10")
}

tasks.jacocoTestReport {
        reports {
                xml.required = true
        }
}

tasks.withType<Test> {
        finalizedBy(tasks.jacocoTestReport)
        useJUnitPlatform()
        testLogging {
                exceptionFormat = TestExceptionFormat.FULL
                events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
                showStandardStreams = true
        }

}
