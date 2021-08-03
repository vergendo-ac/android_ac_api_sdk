package city.augmented.api.infrastructure

object ServerProperties {
    val serverUrls: List<ServerUrl> = listOf(
        ServerUrl("developer.augmented.city"),
        ServerUrl("185.162.94.228:25000", false),
        ServerUrl("developer.mirror.augmented.city"),
        ServerUrl("185.162.94.228:35000", false),
        ServerUrl("developer.reserve.augmented.city"),
        ServerUrl("178.249.69.197:36000", false),
        ServerUrl("178.249.69.197:37000", false),
        ServerUrl("developer.testing.augmented.city"),
        ServerUrl("185.162.94.228:15000", false),
        ServerUrl("185.162.94.228:16000", false),
        ServerUrl("185.162.94.228:17000", false)
    )

    val mainUrl: ServerUrl
        get() = serverUrls[0]
}

class ServerUrl(
    val url: String,
    val useSSL: Boolean = true
)