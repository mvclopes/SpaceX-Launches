package com.mvclopes.spacexlaunches.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.domain.usecase.GetAllLaunchesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllLaunchesUseCase: GetAllLaunchesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private var _state = MutableLiveData<HomeUiState>()
    val state: LiveData<HomeUiState>
        get() = _state

    fun getAllLaunches() {
        viewModelScope.launch {
            getAllLaunchesUseCase()
                .flowOn(dispatcher)
                .onStart { showLoading() }
                .catch { throwable -> handleOnError(throwable) }
                .collect { launches -> handleOnSuccess(launches)}
        }
    }

    private fun showLoading() {
        _state.value = HomeUiState.Loading
    }

    private fun handleOnSuccess(launches: List<Launch>) {
        _state.value = HomeUiState.Success(launches)
    }

    private fun handleOnError(throwable: Throwable) {
        _state.value = HomeUiState.Error(throwable.message)
    }
}
