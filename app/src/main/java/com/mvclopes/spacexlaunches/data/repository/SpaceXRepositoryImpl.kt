package com.mvclopes.spacexlaunches.data.repository

import com.mvclopes.spacexlaunches.data.datasource.SpaceXDataSource
import com.mvclopes.spacexlaunches.data.mapper.toDomain
import com.mvclopes.spacexlaunches.data.mapper.toEntity
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SpaceXRepositoryImpl(private val datasource: SpaceXDataSource): SpaceXRepository {

    override fun getAllLaunches(): Flow<List<Launch>> {
        return datasource.getAllLaunches().map { it.toDomain() }
    }

    override fun getFavoriteLaunches(): Flow<List<Launch>> {
        return datasource.getFavoriteLaunches().map { it.toDomain() }
    }

    override fun insertAll(launches: List<Launch>): Flow<Unit> {
        return datasource.insertAll(launches.toEntity())
    }

    override fun insertLaunch(launch: Launch): Flow<Unit> {
        return datasource.insertLaunch(launch.toEntity())
    }

    override fun getSpecificLaunch(flightNumber: Int): Flow<Launch> {
        return datasource.getSpecificLaunch(flightNumber).map { it.toDomain() }
    }

    override fun deleteLaunch(launch: Launch): Flow<Unit> {
        return datasource.deleteLaunch(launch.toEntity())
    }
}
