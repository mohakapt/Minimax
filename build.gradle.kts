plugins {
    kotlin("jvm") version "1.9.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}
