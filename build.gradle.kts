import java.io.ByteArrayOutputStream
import java.time.Instant.now
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.jmailen.kotlinter") version "2.3.2"
    id("org.sonarqube") version "2.8"
    id("io.gitlab.arturbosch.detekt") version "1.8.0"
    id("com.google.cloud.tools.jib") version "2.2.0"
    id("org.owasp.dependencycheck") version "5.3.2.1"
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
    jacoco
}

group = "org.dripto"
java.sourceCompatibility = JavaVersion.VERSION_11

version = object {
    private val version by lazy {
        ByteArrayOutputStream().use {
            exec {
                commandLine = listOf("git", "rev-parse", "--short=6", "--verify", "HEAD")
                standardOutput = it
            }
            it.toString().trim()
        }
    }

    override fun toString() = version
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.strikt:strikt-core:0.26.0")
    testImplementation("io.mockk:mockk:1.10.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
project.tasks["sonarqube"].dependsOn("detekt")
project.tasks["sonarqube"].dependsOn("jacocoTestReport")

tasks {
    jacocoTestReport {
        reports {
            xml.isEnabled = true
            csv.isEnabled = false
            html.isEnabled = false
        }
        dependsOn(test)
    }
}

jacoco {
    toolVersion = "0.8.5"
    reportsDir = file("$buildDir/reports/jacoco/")
}

detekt {
    ignoreFailures = true
    reports {
        xml {
            enabled = true
            destination = file("$buildDir/reports/detekt/report.xml")
        }
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "driptaroop_Github-Issue-Tracker")
        property("sonar.organization", "driptaroop-sonarcloud")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.login", System.getProperty("SONARCLOUD_TOKEN"))
        property("sonar.kotlin.detekt.reportPaths", "$buildDir/reports/detekt/report.xml")
        property("sonar.coverage.jacoco.xmlReportPaths", "$buildDir/reports/jacoco/test/jacocoTestReport.xml")
    }
}

// Jib builds optimized Java docker containers
jib {
    container {
        labels = mapOf("service" to rootProject.name)
        ports = listOf("8080", "7091")
        creationTime = now().toString()
    }
    from {
        image = "amazoncorretto:11"
    }
    to {
        image = "${rootProject.name}:${version.toString().take(6)}"
        tags = setOf(version.toString(), "latest")
    }
}
