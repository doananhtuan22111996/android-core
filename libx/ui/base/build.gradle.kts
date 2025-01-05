import vn.core.buildSrc.Configs

plugins {
    vn.core.plugins.androidLibrary
    vn.core.plugins.androidPublishing
}

android {
    namespace = Configs.UiBase.NAMESPACE

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

publishing {
    publications {
        create<MavenPublication>(Configs.Artifact.ARTIFACT_APP_ID) {
            afterEvaluate {
                from(components["all"])
            }
            groupId = Configs.Artifact.GROUP_ID // Replace with your GitHub username
            artifactId = Configs.Artifact.ARTIFACT_APP_ID
            version = Configs.Artifact.VERSION // Set your desired version here
        }
    }
}

dependencies {
    implementation(project(Configs.Module.DOMAIN))
}
