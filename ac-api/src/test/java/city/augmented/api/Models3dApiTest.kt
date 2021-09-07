package city.augmented.api

import city.augmented.api.infrastructure.ACApiClient
import city.augmented.api.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class Models3dApiTest {

    private val apiClient: ACApiClient = ACApiClient(
        okHttpClientBuilder = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    )

    private val modelsApiClient: Models3dApiClient = Models3dApiClient(apiClient)

    @Test
    fun getModelsList_realServerTest() = runBlocking {

        var objectsNumber = 0

        modelsApiClient.get3dModels()
            .ifLeft {
                printFailure(it.message)
            }
            .ifRight {
                objectsNumber = it.size
                printSuccess(it.toString())
            }

        Assert.assertTrue(objectsNumber > 0)
        return@runBlocking
    }
}