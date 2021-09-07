package city.augmented.api

import arrow.core.Either
import city.augmented.api.apis.Models3dApi
import city.augmented.api.entity.ApiError
import city.augmented.api.entity.PlatformType
import city.augmented.api.infrastructure.ACApiClient
import city.augmented.api.model.Model3dDto

class Models3dApiClient(private val apiClient: ACApiClient) {
    private val models3dApi: Models3dApi
        get() = apiClient.getModels3dApi()

    /**
     * Get list of available 3D objects with link on binary resources such as glb model or unity bundle.
     *
     * @param platform configures the return of objects that are present for the specified platform.
     * With param null will returns all available objects for all platforms.
     * @return Model3dDto or ApiError
     */
    suspend fun get3dModels(platform: PlatformType? = null): Either<ApiError, List<Model3dDto>> =
        safeInvoke { models3dApi.getModelsList(platform) }
}