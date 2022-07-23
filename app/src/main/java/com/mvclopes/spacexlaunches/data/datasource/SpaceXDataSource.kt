package com.mvclopes.spacexlaunches.data.datasource

import com.mvclopes.spacexlaunches.data.model.LaunchResponse
import kotlinx.coroutines.flow.Flow

interface SpaceXDataSource {
    fun getAllLaunches(): Flow<List<LaunchResponse>>
}
