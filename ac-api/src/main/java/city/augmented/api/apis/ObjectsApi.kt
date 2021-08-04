package city.augmented.api.apis

import city.augmented.api.infrastructure.ServerProperties.apiPrefix
import city.augmented.api.infrastructure.ServerProperties.rpcPrefix
import city.augmented.api.model.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ObjectsApi {

    /**
     * Add AR object by local pose
     * Add a custom object by 3d pose
     * Responses:
     *  - 200: Object processed
     *  - 400: Bad request
     *  - 500: Internal Server Error
     *
     * @param objectWithPose
     * @return [AddObjectResult]
     */
    @POST("$apiPrefix/object/pose")
    suspend fun addObjectByLocalPose(
        @Body objectWithPose: AddObjectWithLocalPoseDto
    ): Response<AddObjectResult>

    /**
     * Add AR object by geopose
     * Add a custom object by geopose. See [GeoPose](#section/GeoPose)
     * Responses:
     *  - 200: Object processed
     *  - 400: Bad request
     *  - 500: Internal Server Error
     *
     * @param objectWithGeoPose
     * @return [AddObjectResult]
     */
    @POST("$apiPrefix/object/geopose")
    suspend fun addObjectByGeoPose(
        @Body objectWithGeoPose: AddObjectWithGeoPoseDto
    ): Response<AddObjectResult>

    /**
     * Add AR object by geopose from angles
     * Add a custom object by geopose from angles. See [GeoPose](#section/GeoPose).
     * Geopose rotation is expressed as a heading, pitch, and roll.
     * Heading is the rotation about the negative z axis. Pitch is the rotation about the negative y axis.
     * Roll is the rotation about the positive x axis. Applied in that order.
     * Responses:
     *  - 200: Object processed
     *  - 400: Bad request
     *  - 500: Internal Server Error
     *
     * @param objectWithGeoPoseFromAngles
     * @return [AddObjectResult]
     */
    @POST("$apiPrefix/object/geopose_from_angles")
    suspend fun addObjectByGeoPoseFromAngles(
        @Body objectWithGeoPoseFromAngles: AddObjectWithGeoPoseFromAnglesDto
    ): Response<AddObjectResult>

    /**
     * Add AR object by image
     * Add a custom object by marked image. The image must be localizable in the system.
     * Before calling the method check the image with localization api.
     * Responses:
     *  - 200: Object processed
     *  - 400: Bad request
     *  - 500: Internal Server Error
     *
     * @param description
     * @param image A JPEG-encoded image, must include GPS data in EXIF tags
     * @return [AddObjectResult]
     */
    @Multipart
    @POST("$apiPrefix/object")
    suspend fun addObjectByImage(
        @Part("description") description: ObjectWithMarkedImageDto,
        @Part image: MultipartBody.Part
    ): Response<AddObjectResult>

    /**
     * Update AR object local pose
     * Update object local pose
     * Responses:
     *  - 200: Object processed
     *  - 400: Bad request
     *  - 500: Internal Server Error
     *
     * @param placeholderId
     * @param updateObjectWithPose
     * @return [UpdateObjectStatusDto]
     */
    @PUT("$apiPrefix/object/{placeholder_id}/pose")
    suspend fun updateObjectLocalPose(
        @Path("placeholder_id") placeholderId: Int,
        @Body updateObjectWithPose: UpdateObjectWithLocalPoseDto
    ): Response<UpdateObjectStatusDto>

    /**
     * Update AR object geopose
     * Update object geopose. See [GeoPose](#section/GeoPose)
     * Responses:
     *  - 200: Object processed
     *  - 400: Bad request
     *  - 500: Internal Server Error
     *
     * @param placeholderId
     * @param updateObjectWithGeoPose
     * @return [UpdateObjectStatusDto]
     */
    @PUT("$apiPrefix/object/{placeholder_id}/geopose")
    suspend fun updateObjectGeoPose(
        @Path("placeholder_id") placeholderId: Int,
        @Body updateObjectWithGeoPose: UpdateObjectWithGeoPoseDto
    ): Response<UpdateObjectStatusDto>

    /**
     * Update AR object geopose from angles
     * Update object geopose from angles. See [GeoPose](#section/GeoPose)
     * Responses:
     *  - 200: Object processed
     *  - 400: Bad request
     *  - 500: Internal Server Error
     *
     * @param placeholderId
     * @param updateObjectWithGeoPoseFromAngles
     * @return [UpdateObjectStatusDto]
     */
    @PUT("$apiPrefix/object/{placeholder_id}/geopose_from_angles")
    suspend fun updateObjectGeoPoseFromAngles(
        @Path("placeholder_id") placeholderId: Int,
        @Body updateObjectWithGeoPoseFromAngles: UpdateObjectWithGeoPoseFromAnglesDto
    ): Response<UpdateObjectStatusDto>

    /**
     * Update AR object content
     * Update AR object&#39;s content, all properties are not mandatory. Only sent properties would be changed, others remain untouched
     * Responses:
     *  - 200: Object processed
     *  - 400: Bad request
     *  - 500: Internal Server Error
     *
     * @param updateObjectContent
     * @return [UpdateObjectStatusDto]
     */
    @PUT("$apiPrefix/object/{placeholder_id}/content")
    suspend fun updateArObjectContent(
        @Body updateObjectContent: UpdateObjectContentDto
    ): Response<UpdateObjectStatusDto>


    /**
     * Get objects list
     * Get objects by placeholders ids
     * Responses:
     *  - 200: OK
     *
     * @param getObjectsParams
     * @return [List<ARObjectOldDto>]
     */
    @POST("$rpcPrefix/get_stickers_by_placeholders")
    suspend fun getObjectsByPlaceholdersIds(
        @Body getObjectsParams: GetObjectsByPlaceholdersIdsDto
    ): Response<List<ARObjectOldDto>>

    /** Get placeholders list by gps
     * Get placeholders list by gps
     * Responses:
     *  - 200: OK
     *
     * @param pLatitude GPS latitude
     * @param pLongitude GPS longitude
     * @param pRadius Search radius
     * @return [List<PlaceholderWithGpsDto>]
     */
    @GET("$rpcPrefix/get_near_placeholders")
    suspend fun getPlaceholdersByGps(
        @Query("p_latitude") pLatitude: Double,
        @Query("p_longitude") pLongitude: Double,
        @Query("p_radius") pRadius: Float
    ): Response<List<PlaceholderWithGpsDto>>
}
