package city.augmented.api.infrastructure

object ServerProperties {
    const val relocSuffix = "api/v2"
    const val rpcSuffix = "rpc"
    const val oscpSuffix = "scrs"

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
    private val host: String,
    private val useSSL: Boolean = true
) {
    companion object {
        private const val URL_PREFIX = "http://"
        private const val URL_SSL_PREFIX = "https://"
        private const val URL_POSTFIX = "/"
    }

    override fun toString(): String = getFormattedUrl()

    fun getFormattedUrl() = StringBuilder().apply {
        if (ServerProperties.serverUrls.contains(this@ServerUrl))
            if (useSSL)
                append(URL_SSL_PREFIX)
            else
                append(URL_PREFIX)
        else
            append(URL_SSL_PREFIX)
        append(host)
        append(URL_POSTFIX)
    }.toString()
}