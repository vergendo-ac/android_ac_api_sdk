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
            .baseUrl(baseUrl.getFormattedUrl())
            .addConverterFactory(scalarsConverterFactory)
            .addConverterFactory(moshiMainFactory)
            .callFactory(usedCallFactory)
            .build()
            .create(serviceClass)
    }
}
