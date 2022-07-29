package com.mvclopes.spacexlaunches.domain.usecase

import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import kotlinx.coroutines.flow.Flow

class IsFavoriteLaunchUseCase(private val repository: SpaceXRepository) {
    operator fun invoke(flightNumber: Int): Flow<Boolean> = repository.isFavoriteLaunch(flightNumber)
}
