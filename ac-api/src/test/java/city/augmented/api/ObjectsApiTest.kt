package city.augmented.api

import city.augmented.api.entity.GpsArea
import city.augmented.api.infrastructure.ACApiClient
import city.augmented.api.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class ObjectsApiTest {

    private val apiClient: ACApiClient = ACApiClient(
        okHttpClientBuilder = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    )

    private val objectsApiClient: ObjectsApiClient = ObjectsApiClient(apiClient)

    @Test
    fun getPlaceholdersByGps_realServerTest() = runBlocking {
        val gpsArea = GpsArea(
            59.934077,
            30.272576
        )

        var placeholderNumber = 0

        objectsApiClient.getPlaceholdersByGps(gpsArea)
            .ifLeft {
                printFailure(it.message)
            }
            .ifRight {
                placeholderNumber = it.size
                printSuccess(it.toString())
            }

        Assert.assertTrue(placeholderNumber > 0)
        return@runBlocking
    }
}