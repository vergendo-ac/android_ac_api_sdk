package city.augmented.api

import arrow.core.Either
import arrow.core.filterOrElse
import city.augmented.api.apis.LocalizerApi
import city.augmented.api.entity.ApiError
import city.augmented.api.infrastructure.ACApiClient
import city.augmented.api.model.*

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
    }.filterOrElse(
        { it.status.code == StatusCode.SUCCESS },
        { ApiError.NotLocalized("Image not localized") }
    )

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