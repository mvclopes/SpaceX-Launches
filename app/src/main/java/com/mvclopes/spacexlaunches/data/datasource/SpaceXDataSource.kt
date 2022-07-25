package com.mvclopes.spacexlaunches.data.datasource

import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity
import com.mvclopes.spacexlaunches.data.model.LaunchResponse
import kotlinx.coroutines.flow.Flow

interface SpaceXDataSource {
    fun getAllLaunches(): Flow<List<LaunchResponse>>
    fun getFavoriteLaunches(): Flow<List<LaunchEntity>>
    fun insertAll(launches: List<LaunchEntity>): Flow<Unit>
    fun insertLaunch(launch: LaunchEntity): Flow<Unit>
    fun getSpecificLaunch(flightNumber: Int): Flow<LaunchEntity>
    fun deleteLaunch(launch: LaunchEntity): Flow<Unit>
}
