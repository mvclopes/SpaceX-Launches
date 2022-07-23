package com.mvclopes.spacexlaunches.domain.usecase

import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import kotlinx.coroutines.flow.Flow

class GetAllLaunchesUseCase(private val repository: SpaceXRepository) {

    operator fun invoke(): Flow<List<Launch>> = repository.getAllLaunches()
}
