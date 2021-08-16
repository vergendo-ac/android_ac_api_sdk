package city.augmented.api.apis

import city.augmented.api.entity.PlatformType
import city.augmented.api.infrastructure.ServerProperties.apiPrefix
import city.augmented.api.infrastructure.ServerProperties.prefix3d
import city.augmented.api.model.Model3dDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Models3dApi {

    @GET("$apiPrefix/$prefix3d/all")
    suspend fun getModelsList(
        @Query(value = "platform") platform: PlatformType?
    ): Response<List<Model3dDto>>
}