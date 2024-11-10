import vn.core.buildSrc.Configs
import vn.core.plugins.repoUri

plugins {
    vn.core.plugins.androidLibrary
    vn.core.plugins.androidPublishing
}

android {
    namespace = Configs.Data.namespace
}

publishing {
    repositories {
        maven {
            url = repoUri(repoName = "android-core")
        }
    }
    publications {
        create<MavenPublication>(Configs.Artifact.artifactDataId) {
            afterEvaluate {
                from(components["all"])
            }
            groupId = Configs.Artifact.groupId // Replace with your GitHub username
            artifactId = Configs.Artifact.artifactDataId
            version = Configs.Artifact.version // Set your desired version here
        }
    }
}

dependencies {
    implementation(project(Configs.Module.domain))
}
