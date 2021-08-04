package city.augmented.api

import arrow.core.Either
import city.augmented.api.apis.ObjectsApi
import city.augmented.api.entity.ApiError
import city.augmented.api.entity.GpsArea
import city.augmented.api.infrastructure.ACApiClient
import city.augmented.api.model.*

class ObjectsApiClient(private val apiClient: ACApiClient) {
    private val objectsApi: ObjectsApi
        get() = apiClient.getObjectsService()

    suspend fun addObjectByLocalPose(objectWithPose: AddObjectWithLocalPoseDto)
            : Either<ApiError, AddObjectResult> = safeInvoke {
        objectsApi.addObjectByLocalPose(objectWithPose)
    }

    suspend fun addObjectByGeoPose(
        objectWithGeoPose: AddObjectWithGeoPoseDto
    ): Either<ApiError, AddObjectResult> = safeInvoke {
        objectsApi.addObjectByGeoPose(objectWithGeoPose)
    }

    suspend fun addObjectByGeoPoseFromAngles(
        objectWithGeoPoseFromAngles: AddObjectWithGeoPoseFromAnglesDto
    ): Either<ApiError, AddObjectResult> = safeInvoke {
        objectsApi.addObjectByGeoPoseFromAngles(objectWithGeoPoseFromAngles)
    }

    suspend fun addObjectByImage(
        description: ObjectWithMarkedImageDto,
        image: ByteArray
    ): Either<ApiError, AddObjectResult> = safeInvoke {
        objectsApi.addObjectByImage(
            description,
            image.toMultipartBody()
        )
    }

    suspend fun updateObjectLocalPose(
        placeholderId: Int,
        updateObjectWithPose: UpdateObjectWithLocalPoseDto
    ): Either<ApiError, UpdateObjectStatusDto> = safeInvoke {
        objectsApi.updateObjectLocalPose(placeholderId, updateObjectWithPose)
    }

    suspend fun updateObjectGeoPose(
        placeholderId: Int,
        updateObjectWithGeoPose: UpdateObjectWithGeoPoseDto
    ): Either<ApiError, UpdateObjectStatusDto> = safeInvoke {
        objectsApi.updateObjectGeoPose(placeholderId, updateObjectWithGeoPose)
    }

    suspend fun updateObjectGeoPoseFromAngles(
        placeholderId: Int,
        updateObjectWithGeoPoseFromAngles: UpdateObjectWithGeoPoseFromAnglesDto
    ): Either<ApiError, UpdateObjectStatusDto> = safeInvoke {
        objectsApi.updateObjectGeoPoseFromAngles(placeholderId, updateObjectWithGeoPoseFromAngles)
    }

    suspend fun updateArObjectContent(
        updateObjectContent: UpdateObjectContentDto
    ): Either<ApiError, UpdateObjectStatusDto> = safeInvoke {
        objectsApi.updateArObjectContent(updateObjectContent)
    }

    suspend fun getObjectsByPlaceholdersIds(
        getObjectsParams: GetObjectsByPlaceholdersIdsDto
    ): Either<ApiError, List<ARObjectOldDto>> = safeInvoke {
        objectsApi.getObjectsByPlaceholdersIds(getObjectsParams)
    }

    suspend fun getPlaceholdersByGps(
        gpsArea: GpsArea
    ): Either<ApiError, List<PlaceholderWithGpsResultDto>> = safeInvoke {
        objectsApi.getPlaceholdersByGps(
            gpsArea.latitude,
            gpsArea.longitude,
            gpsArea.radius
        )
    }
}