plugins {
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.androidLibrary)
    id("kotlin-kapt")
    alias(libs.plugins.androidHilt)
    `maven-publish`
}

android {
    namespace = Configs.Data.namespace
    compileSdk = Configs.targetSdk

    defaultConfig {
        minSdk = Configs.minSdk

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
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }
    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }
    buildFeatures {
        buildConfig = true
    }
    publishing {
        multipleVariants("all") {
            allVariants()
            withSourcesJar()
        }
    }
}

publishing {
    val ghUsername = System.getenv("GH_USERNAME")
    val ghPassword = System.getenv("GH_TOKEN")
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
        create<MavenPublication>("mavenAndroid") {
            afterEvaluate {
                from(components["all"])
            }
            groupId = "vn.core.libx" // Replace with your GitHub username
            artifactId = "data"
            version = "1.0.0" // Set your desired version here
        }
    }
}

dependencies {
    implementation(project(":libx:domain"))

    implementation(libs.androidxCoreKtx)
    implementation(libsCore.androidx.room.common)
    implementation(libsCore.androidx.room.ktx)
    annotationProcessor(libs.androidxRoomCompiler)
    kapt(libs.androidxRoomCompiler)
    implementation(libs.androidxPagingCommon)
    implementation(libs.androidxCoreCoroutines)
    implementation(libs.androidxSecurity)
    implementation(libs.androidxHilt)
    kapt(libs.androidxHiltCompiler)
    implementation(libs.retrofit)
    implementation(libs.retrofitGson)
    implementation(libs.loggerOkhttp)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxJunit)
    androidTestImplementation(libs.androidxEspressoCore)

    implementation(libs.loggerTimber)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
