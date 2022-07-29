package com.mvclopes.spacexlaunches.domain.usecase

import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteLaunchesUseCase(private val repository: SpaceXRepository) {
    operator fun invoke(): Flow<List<Launch>> = repository.getFavoriteLaunches()
}
