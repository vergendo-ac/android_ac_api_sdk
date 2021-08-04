package city.augmented.api

import city.augmented.api.infrastructure.ApiClient
import city.augmented.api.model.LocationDto
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalizerApiTest {

    private val apiClient: ApiClient = ApiClient(
        okHttpClientBuilder = OkHttpClient().newBuilder()
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    )

    private val localizerApiClient: LocalizerApiClient = LocalizerApiClient(apiClient)


    val location = LocationDto(
        59.934077,
        30.272576,
        12.21
    )

    @Test
    fun prepareLocalizer_test() = runBlocking {
        var code = -1
        val successCode = 0
        localizerApiClient.prepareLocalizer(location)
            .ifLeft {
                println("Failure: ${it.message}")
            }
            .ifRight {
                code = it.status.code
                println("Success: $it")
            }
        Assert.assertEquals(successCode, code)
        return@runBlocking
    }

}