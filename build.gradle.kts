plugins {
    id("maven-publish")
    id("java")
}

group = "com.teamresourceful"
version = "1.0.2"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:23.0.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

java {
    withSourcesJar()
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                name.set("Yet Another Binary Notation")
                description.set("Resourceful Bees is a mod that adds bees to Minecraft.")
                url.set("https://github.com/Team-Resourceful/YetAnotherBinaryNotation")

                licenses {
                    license {
                        name.set("MIT")
                    }
                }

                scm {
                    connection.set("git:https://github.com/Team-Resourceful/YetAnotherBinaryNotation.git")
                    developerConnection.set("git:https://github.com/Team-Resourceful/YetAnotherBinaryNotation.git")
                    url.set("https://github.com/Team-Resourceful/YetAnotherBinaryNotation")
                }
            }
        }
    }

    repositories {
        maven {
            setUrl("https://maven.resourcefulbees.com/repository/maven-releases/")

            credentials {
                username = System.getenv("MAVEN_USER")
                password = System.getenv("MAVEN_PASS")
            }
        }
    }
}