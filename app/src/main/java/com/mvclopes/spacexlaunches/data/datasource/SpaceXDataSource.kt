package com.mvclopes.spacexlaunches.data.datasource

import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity
import com.mvclopes.spacexlaunches.data.model.LaunchResponse
import kotlinx.coroutines.flow.Flow

interface SpaceXDataSource {
    fun getAllLaunches(): Flow<List<LaunchResponse>>
    fun getFavoriteLaunches(): Flow<List<LaunchEntity>>
    fun insertLaunch(launch: LaunchEntity): Flow<Unit>
    fun isFavoriteLaunch(flightNumber: Int): Flow<Boolean>
    fun deleteLaunch(launch: LaunchEntity): Flow<Unit>
    fun getLastYearLaunches(): Flow<List<LaunchResponse>>
    fun getOnlyLaunchSuccess(): Flow<List<LaunchResponse>>
}
