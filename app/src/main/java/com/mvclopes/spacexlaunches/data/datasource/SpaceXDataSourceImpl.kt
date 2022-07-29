package com.mvclopes.spacexlaunches.data.datasource

import com.mvclopes.spacexlaunches.data.datasource.local.LocalDataSource
import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity
import com.mvclopes.spacexlaunches.data.datasource.remote.RemoteDataSource
import com.mvclopes.spacexlaunches.data.model.LaunchResponse
import kotlinx.coroutines.flow.Flow

class SpaceXDataSourceImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): SpaceXDataSource {

    override fun getAllLaunches(): Flow<List<LaunchResponse>> {
        return remoteDataSource.getAllLaunches()
    }

    override fun getFavoriteLaunches(): Flow<List<LaunchEntity>> {
        return localDataSource.getFavoriteLaunches()
    }

    override fun insertLaunch(launch: LaunchEntity): Flow<Unit> {
        return localDataSource.insertLaunch(launch)
    }

    override fun isFavoriteLaunch(flightNumber: Int): Flow<Boolean> {
        return localDataSource.isFavoriteLaunch(flightNumber)
    }

    override fun deleteLaunch(launch: LaunchEntity): Flow<Unit> {
        return localDataSource.deleteLaunch(launch)
    }

    override fun getLastYearLaunches(): Flow<List<LaunchResponse>> {
        return remoteDataSource.getLastYearLaunches()
    }

    override fun getOnlyLaunchSuccess(): Flow<List<LaunchResponse>> {
        return remoteDataSource.getOnlyLaunchSuccess()
    }
}
