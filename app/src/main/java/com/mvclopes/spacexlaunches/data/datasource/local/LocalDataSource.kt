package com.mvclopes.spacexlaunches.data.datasource.local

import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getFavoriteLaunches(): Flow<List<LaunchEntity>>
    fun insertAll(launches: List<LaunchEntity>): Flow<Unit>
    fun insertLaunch(launch: LaunchEntity): Flow<Unit>
}
