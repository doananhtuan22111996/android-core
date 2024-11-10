import vn.core.buildSrc.Configs

plugins {
    vn.core.plugins.androidLibrary
    vn.core.plugins.androidPublishing
}

android {
    namespace = Configs.UiBase.namespace

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

publishing {
    publications {
        create<MavenPublication>(Configs.Artifact.artifactAppId) {
            afterEvaluate {
                from(components["all"])
            }
            groupId = Configs.Artifact.groupId // Replace with your GitHub username
            artifactId = Configs.Artifact.artifactAppId
            version = Configs.Artifact.version // Set your desired version here
        }
    }
}

dependencies {
    implementation(project(Configs.Module.domain))
}
