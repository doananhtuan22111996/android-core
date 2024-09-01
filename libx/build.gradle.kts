plugins {
    id("java-library")
    id("maven-publish")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/${System.getenv("USERNAME")}/android-core")
            credentials {
                username =  System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
            groupId = "com.github.${System.getenv("USERNAME")}" // Replace with your GitHub username
            artifactId = "android-core"
            version = "1.0.0" // Set your desired version here
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}