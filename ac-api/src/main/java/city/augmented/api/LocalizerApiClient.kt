package city.augmented.api

import arrow.core.Either
import city.augmented.api.apis.LocalizerApi
import city.augmented.api.entity.ApiError
import city.augmented.api.infrastructure.ACApiClient
import city.augmented.api.model.ImageDescriptionDto
import city.augmented.api.model.LocalizationResultDto
import city.augmented.api.model.LocalizationStatusDto
import city.augmented.api.model.LocationDto

class LocalizerApiClient(private val apiClient: ACApiClient) {
    private val localizer: LocalizerApi
        get() = apiClient.getLocalizerService()

    suspend fun localize(
        description: ImageDescriptionDto,
        imageBytes: ByteArray
    ): Either<ApiError, LocalizationResultDto> = safeInvoke {
        localizer.localize(
            description,
            imageBytes.toMultipartBody()
        )
    }

    suspend fun prepareLocalizer(location: LocationDto): Either<ApiError, LocalizationStatusDto> =
        safeInvoke {
            localizer.prepare(
                location.latitude,
                location.longitude,
                location.altitude,
                location.hDop
            )
        }
}