plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.android.hilt)
    id("maven-publish")
}

android {
    namespace = "vn.core.data"
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
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
}

dependencies {
    implementation(project(":libx:domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.room.common)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.paging.common.android)
    implementation(libs.androidx.corecoroutines)
    implementation(libs.androidx.hilt)
    implementation(libs.androidx.security.crypto.ktx)
    kapt(libs.androidx.hiltcompiler)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.interceptor)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.timber)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
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
            groupId = "vn.core.libx" // Replace with your GitHub username
            artifactId = "data"
            version = "1.0.0" // Set your desired version here
        }
    }
}