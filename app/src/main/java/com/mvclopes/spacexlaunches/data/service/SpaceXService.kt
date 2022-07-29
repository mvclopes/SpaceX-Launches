package com.mvclopes.spacexlaunches.data.service

import com.mvclopes.spacexlaunches.data.model.LaunchResponse
import retrofit2.http.GET

interface SpaceXService {

    @GET("launches")
    suspend fun getAllLaunches(): List<LaunchResponse>

    @GET("launches?launch_year=2020")
    suspend fun getLastYearLaunches(): List<LaunchResponse>

    @GET("launches?launch_success=true")
    suspend fun getOnlyLaunchSuccess(): List<LaunchResponse>
}
