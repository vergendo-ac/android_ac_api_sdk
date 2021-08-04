package city.augmented.api

import city.augmented.api.infrastructure.ApiClient
import city.augmented.api.model.GpsDto
import city.augmented.api.model.ImageDescriptionDto
import city.augmented.api.model.LocationDto
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalizerApiTest {

    private val apiClient: ApiClient = ApiClient(
//        okHttpClientBuilder = OkHttpClient().newBuilder()
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    )

    private val localizerApiClient: LocalizerApiClient = LocalizerApiClient(apiClient)

    @Test
    fun prepareLocalizer_test() = runBlocking {
        val location = LocationDto(
            59.934077,
            30.272576,
            12.21
        )

        var code = -1
        val successCode = 0

        localizerApiClient.prepareLocalizer(location)
            .ifLeft {
                printFailure(it.message)
            }
            .ifRight {
                code = it.status.code
                printSuccess(it.toString())
            }

        Assert.assertEquals(successCode, code)
        return@runBlocking
    }

    @Test
    fun localize_test() = runBlocking {
        val imageDescription = ImageDescriptionDto(GpsDto(60.0309083, 30.2414354), 90)
        val imageBytes = LocalizerApiTest::class.java.getResource("image.jpg")!!.readBytes()

        val successCode = 0
        var code = -1

        localizerApiClient.localize(imageDescription, imageBytes)
            .ifLeft {
                printFailure(it.message)
            }
            .ifRight {
                code = it.status.code
                printSuccess(it.toString())
            }

        Assert.assertEquals(successCode, code)
        return@runBlocking
    }

    private fun printSuccess(message: String) = println("Success: $message")
    private fun printFailure(message: String) = println("Failure: $message")

}