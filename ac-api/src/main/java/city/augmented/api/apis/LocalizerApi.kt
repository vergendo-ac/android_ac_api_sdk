package city.augmented.api.apis

import city.augmented.api.model.ImageDescriptionDto
import city.augmented.api.model.LocalizationHintDto
import city.augmented.api.model.LocalizationResultDto
import city.augmented.api.model.LocalizationStatusDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface LocalizerApi {
    /**
     * Localize camera
     * Localize uploaded image. Return camera pose and optional placeholders scene, surfaces scene
     * and objects content. Camera, placeholders and surfaces coordinates are local coordinates in
     * reconstruction coordinate system identified by reconstruction id.
     *
     * Responses:
     *  - 200: Localization result
     *  - 400: Bad request
     *  - 500: Internal Server Error
     *
     * @param description
     * @param image A JPEG-encoded image
     * @param hint  (optional)
     * @return [LocalizationResultDto]
     */
    @Multipart
    @POST("localizer/localize")
    suspend fun localize(
        @Part("description") description: ImageDescriptionDto,
        @Part image: MultipartBody.Part,
        @Part("hint") hint: LocalizationHintDto? = null
    ): Response<LocalizationResultDto>

    /**
     * Prepare localization session
     * Prepare for localization for given geolocation. Causes server to load nearby reconstructions for localization. Returns an error when localization in this location is not possible.
     * Responses:
     *  - 200: Status
     *  - 400: Bad request
     *  - 500: Internal Server Error
     *
     * @param lat GPS latitude
     * @param lon GPS longitude
     * @param alt GPS altitude (optional) (optional)
     * @param dop GPS HDOP (optional) (optional)
     * @return [LocalizationStatusDto]
     */
    @GET("localizer/prepare")
    suspend fun prepare(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("alt") alt: Double? = null,
        @Query("dop") dop: Float? = null
    ): Response<LocalizationStatusDto>
}
