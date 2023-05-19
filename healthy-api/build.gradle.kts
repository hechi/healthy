import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.10"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "2.7.10"
    kotlin("plugin.spring") version "2.7.10"
}

group = "me.hechenberger"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.yaml:snakeyaml:+")
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
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.+")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:+")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.+")
    implementation("com.google.guava:guava:+")
    // logging
//    implementation("io.github.microutils:kotlin-logging:+")
    implementation("io.github.microutils:kotlin-logging-jvm:+")
    implementation("com.squareup.okhttp3:okhttp:+")
    // lombok
    compileOnly("org.projectlombok:lombok:+")
    annotationProcessor("org.projectlombok:lombok:+")
    testCompileOnly("org.projectlombok:lombok:+")
    testAnnotationProcessor("org.projectlombok:lombok:+")
//    // vavr
//    implementation("io.vavr:vavr:+")

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
