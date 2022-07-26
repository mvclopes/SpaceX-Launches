package com.mvclopes.spacexlaunches.domain.repository

import com.mvclopes.spacexlaunches.domain.model.Launch
import kotlinx.coroutines.flow.Flow

interface SpaceXRepository {
    fun getAllLaunches(): Flow<List<Launch>>
    fun getFavoriteLaunches(): Flow<List<Launch>>
    fun insertLaunch(launch: Launch): Flow<Unit>
    fun getSpecificLaunch(flightNumber: Int): Flow<Launch>
    fun deleteLaunch(launch: Launch): Flow<Unit>
    fun getLastYearLaunches(): Flow<List<Launch>>
    fun getOnlyLaunchSuccess(): Flow<List<Launch>>
}
