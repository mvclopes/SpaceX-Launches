package com.mvclopes.spacexlaunches.domain.repository

import com.mvclopes.spacexlaunches.domain.model.Launch
import kotlinx.coroutines.flow.Flow

interface SpaceXRepository {
    fun getAllLaunches(): Flow<List<Launch>>
}
