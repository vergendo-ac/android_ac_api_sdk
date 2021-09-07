package city.augmented.api

import arrow.core.Either
import arrow.core.Left
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
    ): Either<ApiError, LocalizationResultDto> {
        val result = safeInvoke {
            localizer.localize(
                description,
                imageBytes.toMultipartBody()
            )
        }
        result.fold({}, {
            if (it.status.code == StatusCode.FAILURE)
                return Left(ApiError.CantLocalize)
        })
        return result
    }

    suspend fun localizeWithCustomSticker(
        description: ImageDescriptionDto,
        imageBytes: ByteArray
    ): Either<ApiError, LocalizationResultRawDto> {
        val result = safeInvoke {
            localizer.localizeWithCustomSticker(
                description,
                imageBytes.toMultipartBody()
            )
        }
        result.fold({}, {
            if (it.status.code == StatusCode.FAILURE)
                return Left(ApiError.CantLocalize)
        })
        return result
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