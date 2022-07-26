package com.mvclopes.spacexlaunches.data.datasource.remote

import com.mvclopes.spacexlaunches.data.model.LaunchResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllLaunches(): Flow<List<LaunchResponse>>
    fun getLastYearLaunches(): Flow<List<LaunchResponse>>
    fun getOnlyLaunchSuccess(): Flow<List<LaunchResponse>>
}
