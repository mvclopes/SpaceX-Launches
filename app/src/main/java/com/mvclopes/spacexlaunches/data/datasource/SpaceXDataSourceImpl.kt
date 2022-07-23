package com.mvclopes.spacexlaunches.data.datasource

import com.mvclopes.spacexlaunches.data.datasource.remote.RemoteDataSource
import com.mvclopes.spacexlaunches.data.model.LaunchResponse
import kotlinx.coroutines.flow.Flow

class SpaceXDataSourceImpl(
    private val remoteDataSource: RemoteDataSource
): SpaceXDataSource {

    override fun getAllLaunches(): Flow<List<LaunchResponse>> {
        return remoteDataSource.getAllLaunches()
    }
}
