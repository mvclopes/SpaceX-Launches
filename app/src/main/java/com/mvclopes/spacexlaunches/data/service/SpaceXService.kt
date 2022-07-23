package com.mvclopes.spacexlaunches.data.service

import com.mvclopes.spacexlaunches.data.model.LaunchResponse
import retrofit2.http.GET

interface SpaceXService {

    @GET("launches")
    suspend fun getAllLaunches(): List<LaunchResponse>
}
