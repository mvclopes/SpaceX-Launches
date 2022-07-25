package com.mvclopes.spacexlaunches.domain.usecase

import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import kotlinx.coroutines.flow.Flow

class IsFavoriteLaunchUseCase(private val repository: SpaceXRepository) {
    operator fun invoke(flightNumber: Int): Flow<Launch> = repository.getSpecificLaunch(flightNumber)
}
