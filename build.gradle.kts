import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
    application
}

group = "com.anthonycicchetti"
version = "2018"

repositories {
    mavenCentral()
}

application {
    mainClassName = "MainKt"
}


dependencies {
    compile(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}