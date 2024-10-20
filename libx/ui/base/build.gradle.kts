plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    `maven-publish`
}

android {
    namespace = Configs.UiBase.namespace
    compileSdk = Configs.targetSdk

    defaultConfig {
        minSdk = Configs.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }
    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }

    buildFeatures {
        // Include plugin id 'kotlin-kapt' if enable dataBinding
        dataBinding = true
        viewBinding = true
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
            url = uri("${Configs.mavenDomain}/${ghUsername}/android-core")
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
            groupId = "vn.core.libx-ui" // Replace with your GitHub username
            artifactId = "base"
            version = "1.0.0" // Set your desired version here
        }
    }
}

dependencies {
    implementation(project(Configs.Module.domain))
    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxAppcompat)
    implementation(libs.material)
    implementation(libs.androidxNavigationFragmentKtx)
    implementation(libsCore.androidxPagingRuntimeKtx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxJunit)
    androidTestImplementation(libs.androidxEspressoCore)

    implementation(libs.loggerTimber)
}
