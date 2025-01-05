package vn.core.buildSrc

object Configs {
    object Module {
        const val DOMAIN = ":libx:domain"
        const val DATA = ":libx:data"
    }

    object UiBase {
        const val NAMESPACE = "vn.core.ui.base"
    }

    object Data {
        const val NAMESPACE = "vn.core.data"
    }

    object Domain {
        const val NAMESPACE = "vn.core.domain"
    }

    object Artifact {
        const val GROUP_ID = "vn.core.libs"
        const val ARTIFACT_DATA_ID = "data"
        const val ARTIFACT_DOMAIN_ID = "domain"
        const val ARTIFACT_APP_ID = "app"
        const val VERSION = "1.0.1"
    }
}
