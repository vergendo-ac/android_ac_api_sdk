package city.augmented.api.infrastructure

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiClient(
    private var baseUrl: String = ServerProperties.mainUrl,
    private val serializerBuilder: Moshi.Builder = Serializer.moshiBuilder,
    private val okHttpClientBuilder: OkHttpClient.Builder? = null
) {

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(serializerBuilder.build()))
    }

    private val clientBuilder: OkHttpClient.Builder by lazy {
        okHttpClientBuilder ?: defaultClientBuilder
    }

    private val defaultClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient().newBuilder()
    }

    init {
        normalizeBaseUrl()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        val usedCallFactory = clientBuilder.build()
        return retrofitBuilder.callFactory(usedCallFactory).build().create(serviceClass)
    }

    private fun normalizeBaseUrl() {
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/"
        }
    }
}
