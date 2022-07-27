package com.mvclopes.spacexlaunches.data.datasource.local

import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getFavoriteLaunches(): Flow<List<LaunchEntity>>
    fun insertLaunch(launch: LaunchEntity): Flow<Unit>
    fun isFavoriteLaunch(flightNumber: Int): Flow<Boolean>
    fun deleteLaunch(launch: LaunchEntity): Flow<Unit>
}
