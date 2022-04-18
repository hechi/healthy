import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.20"
    kotlin("plugin.spring") version "1.6.20"
}

group = "me.hechenberger"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(KotlinX.coroutines.core)
    implementation("com.github.kittinunf.fuel:fuel:+")
    implementation("com.github.kittinunf.fuel:fuel-coroutines:+")
    implementation("com.github.kittinunf.result:result:+")
    implementation("com.github.kittinunf.result:result-coroutines:+")
    // yaml read
    implementation("com.fasterxml.jackson.core:jackson-databind:+")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:+")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:+")
    implementation("com.google.guava:guava:+")
    // logging
    implementation("io.github.microutils:kotlin-logging:+")
    implementation("com.squareup.okhttp3:okhttp:+")
    // lombok
    compileOnly("org.projectlombok:lombok:+")
    annotationProcessor("org.projectlombok:lombok:+")
    testCompileOnly("org.projectlombok:lombok:+")
    testAnnotationProcessor("org.projectlombok:lombok:+")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
