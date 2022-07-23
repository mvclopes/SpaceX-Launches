package com.mvclopes.spacexlaunches.data.repository

import com.mvclopes.spacexlaunches.data.datasource.SpaceXDataSource
import com.mvclopes.spacexlaunches.data.mapper.toDomain
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SpaceXRepositoryImpl(private val datasource: SpaceXDataSource): SpaceXRepository {

    override fun getAllLaunches(): Flow<List<Launch>> {
        return datasource.getAllLaunches().map { it.toDomain() }
    }
}
