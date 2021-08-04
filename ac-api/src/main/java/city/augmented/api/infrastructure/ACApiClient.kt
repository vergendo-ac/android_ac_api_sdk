package city.augmented.api.infrastructure

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ACApiClient(
    private var baseUrl: ServerUrl = ServerProperties.mainUrl,
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder(),
    private val converterBuilder: Moshi.Builder = ACAdapters.moshiBuilder,
    private val okHttpClientBuilder: OkHttpClient.Builder? = null
) {
    companion object {
        private const val URL_PREFIX = "http://"
        private const val URL_SSL_PREFIX = "https://"
        private const val URL_POSTFIX = "/"
    }

    private val scalarsConverterFactory: ScalarsConverterFactory by lazy {
        ScalarsConverterFactory.create()
    }
    private val moshiMainFactory: MoshiConverterFactory by lazy {
        MoshiConverterFactory.create(converterBuilder.build())
    }
    private val clientBuilder: OkHttpClient.Builder by lazy {
        okHttpClientBuilder ?: defaultClientBuilder
    }
    private val defaultClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
    }

    fun changeServerUrl(newUrl: ServerUrl) {
        baseUrl = newUrl
    }

    fun <S> createService(serviceClass: Class<S>): S {
        val usedCallFactory = clientBuilder.build()
        return retrofitBuilder
            .baseUrl(prepareUrl(baseUrl))
            .addConverterFactory(scalarsConverterFactory)
            .addConverterFactory(moshiMainFactory)
            .callFactory(usedCallFactory)
            .build()
            .create(serviceClass)
    }

    private fun prepareUrl(serverUrl: ServerUrl): String {
        return StringBuilder().apply {
            if (ServerProperties.serverUrls.contains(serverUrl))
                if (serverUrl.useSSL)
                    append(URL_SSL_PREFIX)
                else
                    append(URL_PREFIX)
            else
                append(URL_SSL_PREFIX)
            append(serverUrl.url)
            append(URL_POSTFIX)
        }.toString()
    }
}
