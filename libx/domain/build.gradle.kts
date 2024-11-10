import vn.core.buildSrc.Configs
import vn.core.plugins.repoUri

plugins {
    vn.core.plugins.androidLibrary
    vn.core.plugins.androidPublishing
}

android {
    namespace = Configs.Domain.namespace
}

publishing {
    repositories {
        maven {
            url = repoUri(repoName = "android-core")
        }
    }
    publications {
        create<MavenPublication>(Configs.Artifact.artifactDomainId) {
            afterEvaluate {
                from(components["all"])
            }
            groupId = Configs.Artifact.groupId // Replace with your GitHub username
            artifactId = Configs.Artifact.artifactDomainId
            version = Configs.Artifact.version // Set your desired version here
        }
    }
}
