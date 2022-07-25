package com.mvclopes.spacexlaunches.domain.repository

import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity
import com.mvclopes.spacexlaunches.domain.model.Launch
import kotlinx.coroutines.flow.Flow

interface SpaceXRepository {
    fun getAllLaunches(): Flow<List<Launch>>
    fun getFavoriteLaunches(): Flow<List<Launch>>
    fun insertAll(launches: List<Launch>): Flow<Unit>
    fun insertLaunch(launch: Launch): Flow<Unit>
    fun getSpecificLaunch(flightNumber: Int): Flow<Launch>
    fun deleteLaunch(launch: Launch): Flow<Unit>
}
