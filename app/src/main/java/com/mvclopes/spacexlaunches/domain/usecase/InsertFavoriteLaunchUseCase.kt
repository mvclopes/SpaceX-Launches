package com.mvclopes.spacexlaunches.domain.usecase

import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import kotlinx.coroutines.flow.Flow

class InsertFavoriteLaunchUseCase(private val repository: SpaceXRepository) {
    operator fun invoke(launch: Launch):Flow<Unit> = repository.insertLaunch(launch)
}
