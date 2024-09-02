plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("maven-publish")
}

android {
    namespace = "vn.core.ui.base"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        // Include plugin id 'kotlin-kapt' if enable dataBinding
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":libx:domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.timber)
}

publishing {
    val ghUsername = System.getenv("USERNAME")
    val ghPassword = System.getenv("TOKEN")
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/${ghUsername}/android-core")
            credentials {
                username = ghUsername
                password = ghPassword
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            afterEvaluate {
                from(components["release"])
            }
            groupId = "vn.core.libx-ui" // Replace with your GitHub username
            artifactId = "base"
            version = "1.0.0" // Set your desired version here
        }
    }
}