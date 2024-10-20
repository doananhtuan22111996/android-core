import org.gradle.api.JavaVersion

object Configs {
    const val minSdk = 24
    const val targetSdk = 34
    const val compileSdk = 34
    const val jvmTarget = "21"
    val javaVersion = JavaVersion.VERSION_21
    const val mavenDomain = "https://maven.pkg.github.com"

    object Module {
        const val domain = ":libx:domain"
        const val data = ":libx:data"
    }

    object UiBase {
        const val namespace = "vn.core.ui.base"
    }

    object Data {
        const val namespace = "vn.core.data"
    }
}


