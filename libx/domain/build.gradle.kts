import vn.core.buildSrc.Configs

plugins {
    vn.core.plugins.androidLibrary
    vn.core.plugins.androidPublishing
}

android {
    namespace = Configs.Domain.namespace
}

publishing {
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
