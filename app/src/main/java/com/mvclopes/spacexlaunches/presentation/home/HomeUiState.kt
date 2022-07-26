package com.mvclopes.spacexlaunches.presentation.home

import com.mvclopes.spacexlaunches.domain.model.Launch

sealed class HomeUiState {
    object Loading: HomeUiState()
    data class Success(val launches: List<Launch>): HomeUiState()
    data class Error(val error: String? = null): HomeUiState()
}
