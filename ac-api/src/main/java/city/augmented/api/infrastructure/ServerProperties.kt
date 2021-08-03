package city.augmented.api.infrastructure

object ServerProperties {
    val serverUrls: List<String> = listOf(
        "developer.augmented.city",
        "185.162.94.228:25000",
        "developer.mirror.augmented.city",
        "185.162.94.228:35000",
        "developer.reserve.augmented.city",
        "178.249.69.197:36000",
        "178.249.69.197:37000",
        "developer.testing.augmented.city",
        "185.162.94.228:15000",
        "185.162.94.228:16000",
        "185.162.94.228:17000"
    )

    val mainUrl: String
        get() = serverUrls[0]
}