package vn.core.buildSrc

object Configs {
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

    object Domain {
        const val namespace = "vn.core.domain"
    }

    object Artifact {
        const val groupId = "vn.core.libs"
        const val artifactDataId = "data"
        const val artifactDomainId = "domain"
        const val artifactAppId = "app"
        const val version = "1.0.0"
    }
}
