package com.mvclopes.spacexlaunches.data.datasource.local

import com.mvclopes.spacexlaunches.data.datasource.local.dao.SpaceXDao
import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(private val dao: SpaceXDao): LocalDataSource {
    override fun getFavoriteLaunches(): Flow<List<LaunchEntity>> {
        return flow { emit(dao.getAllLaunches()) }
    }

    override fun insertLaunch(launch: LaunchEntity): Flow<Unit> {
        return flow { emit(dao.insertLaunch(launch)) }
    }

    override fun isFavoriteLaunch(flightNumber: Int): Flow<Boolean> {
        return flow { emit(dao.getSpecificLaunch(flightNumber) != null) }
    }

    override fun deleteLaunch(launch: LaunchEntity): Flow<Unit> {
        return flow { emit(dao.deleteLaunch(launch)) }
    }

}
