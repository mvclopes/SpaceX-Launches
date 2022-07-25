package com.mvclopes.spacexlaunches.data.datasource.remote

import com.mvclopes.spacexlaunches.data.model.LaunchResponse
import com.mvclopes.spacexlaunches.data.service.SpaceXService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(private val service: SpaceXService): RemoteDataSource {

    override fun getAllLaunches(): Flow<List<LaunchResponse>> = flow { emit(service.getAllLaunches()) }
}
