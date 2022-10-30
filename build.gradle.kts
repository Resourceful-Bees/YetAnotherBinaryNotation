plugins {
    id("java")
}

group = "com.teamresourceful"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:23.0.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}