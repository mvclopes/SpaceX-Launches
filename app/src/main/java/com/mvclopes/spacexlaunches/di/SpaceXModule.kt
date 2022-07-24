package com.mvclopes.spacexlaunches.di

import com.mvclopes.spacexlaunches.data.datasource.SpaceXDataSource
import com.mvclopes.spacexlaunches.data.datasource.SpaceXDataSourceImpl
import com.mvclopes.spacexlaunches.data.datasource.remote.RemoteDataSource
import com.mvclopes.spacexlaunches.data.repository.SpaceXRepositoryImpl
import com.mvclopes.spacexlaunches.data.service.ApiModule
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import com.mvclopes.spacexlaunches.domain.usecase.GetAllLaunchesUseCase
import com.mvclopes.spacexlaunches.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.dsl.module

val spaceXModule = module {
    viewModel { HomeViewModel(getAllLaunchesUseCase()) }
}

private fun Scope.getSpaceXService() = ApiModule.spaceXService

private fun Scope.getRemoteDataSource() = RemoteDataSource(service = getSpaceXService())

private fun Scope.getSpaceXDataSource(): SpaceXDataSource {
    return SpaceXDataSourceImpl(remoteDataSource = getRemoteDataSource())
}

private fun Scope.getSpaceXRepository(): SpaceXRepository {
    return SpaceXRepositoryImpl(datasource = getSpaceXDataSource())
}

private fun Scope.getAllLaunchesUseCase() = GetAllLaunchesUseCase(repository = getSpaceXRepository())

