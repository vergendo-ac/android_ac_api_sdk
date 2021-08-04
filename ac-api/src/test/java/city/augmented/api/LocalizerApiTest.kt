package city.augmented.api

import city.augmented.api.infrastructure.ACApiClient
import city.augmented.api.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalizerApiTest {

    private val apiClient: ACApiClient = ACApiClient(
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

        var code = StatusCode.FAILURE

        localizerApiClient.prepareLocalizer(location)
            .ifLeft {
                printFailure(it.message)
            }
            .ifRight {
                code = it.status.code
                printSuccess(it.toString())
            }

        Assert.assertEquals(StatusCode.SUCCESS, code)
        return@runBlocking
    }

    @Test
    fun localize_test() = runBlocking {
        val imageDescription =
            ImageDescriptionDto(GpsDto(60.0309083, 30.2414354), ImageRotation.ROTATION_90)
        val imageBytes = LocalizerApiTest::class.java.getResource("image.jpg")!!.readBytes()

        var code = StatusCode.FAILURE

        localizerApiClient.localize(imageDescription, imageBytes)
            .ifLeft {
                printFailure(it.message)
            }
            .ifRight {
                code = it.status.code
                printSuccess(it.toString())
            }

        Assert.assertEquals(StatusCode.SUCCESS, code)
        return@runBlocking
    }
}