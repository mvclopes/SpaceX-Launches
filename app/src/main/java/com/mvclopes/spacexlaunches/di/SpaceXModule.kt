package com.mvclopes.spacexlaunches.di

import com.mvclopes.spacexlaunches.data.datasource.SpaceXDataSource
import com.mvclopes.spacexlaunches.data.datasource.SpaceXDataSourceImpl
import com.mvclopes.spacexlaunches.data.datasource.local.LocalDataSource
import com.mvclopes.spacexlaunches.data.datasource.local.LocalDataSourceImpl
import com.mvclopes.spacexlaunches.data.datasource.local.db.getDataBase
import com.mvclopes.spacexlaunches.data.datasource.remote.RemoteDataSource
import com.mvclopes.spacexlaunches.data.datasource.remote.RemoteDataSourceImpl
import com.mvclopes.spacexlaunches.data.repository.SpaceXRepositoryImpl
import com.mvclopes.spacexlaunches.data.service.ApiModule
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import com.mvclopes.spacexlaunches.domain.usecase.DeleteLaunchUseCase
import com.mvclopes.spacexlaunches.domain.usecase.GetAllLaunchesUseCase
import com.mvclopes.spacexlaunches.domain.usecase.InsertFavoriteLaunchUseCase
import com.mvclopes.spacexlaunches.domain.usecase.IsFavoriteLaunchUseCase
import com.mvclopes.spacexlaunches.presentation.detail.DetailViewModel
import com.mvclopes.spacexlaunches.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.dsl.module

val spaceXModule = module {
    viewModel { HomeViewModel(getAllLaunchesUseCase()) }
    viewModel {
        DetailViewModel(
            insertFavoriteLaunchUseCase = getInsertFavoriteLaunchUseCase(),
            isFavoriteLaunchUseCase = getIsFavoriteLaunchUseCase(),
            deleteLaunchUseCase = getDeleteLaunchUseCase()
        )
    }
}

private fun Scope.getSpaceXService() = ApiModule.spaceXService

private fun Scope.getRemoteDataSource(): RemoteDataSource {
    return RemoteDataSourceImpl(service = getSpaceXService())
}

private fun Scope.getSpaceXDataSource(): SpaceXDataSource {
    return SpaceXDataSourceImpl(
        remoteDataSource = getRemoteDataSource(),
        localDataSource = getLocalDataSource()
    )
}

private fun Scope.getSpaceXRepository(): SpaceXRepository {
    return SpaceXRepositoryImpl(datasource = getSpaceXDataSource())
}

private fun Scope.getAllLaunchesUseCase() = GetAllLaunchesUseCase(repository = getSpaceXRepository())

private fun Scope.getSpaceXDatabase() = getDataBase(context = get())

private fun Scope.getSpaceXDao() = getSpaceXDatabase().spaceXDAO()

private fun Scope.getLocalDataSource(): LocalDataSource {
    return LocalDataSourceImpl(dao = getSpaceXDao())
}

private fun Scope.getInsertFavoriteLaunchUseCase() = InsertFavoriteLaunchUseCase(repository = getSpaceXRepository())

private fun Scope.getIsFavoriteLaunchUseCase() = IsFavoriteLaunchUseCase(repository = getSpaceXRepository())

private fun Scope.getDeleteLaunchUseCase() = DeleteLaunchUseCase(repository = getSpaceXRepository())

