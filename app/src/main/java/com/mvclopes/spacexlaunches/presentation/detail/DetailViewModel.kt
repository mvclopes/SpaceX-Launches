package com.mvclopes.spacexlaunches.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.domain.usecase.DeleteLaunchUseCase
import com.mvclopes.spacexlaunches.domain.usecase.InsertFavoriteLaunchUseCase
import com.mvclopes.spacexlaunches.domain.usecase.IsFavoriteLaunchUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DetailViewModel(
    private val insertFavoriteLaunchUseCase: InsertFavoriteLaunchUseCase,
    private val isFavoriteLaunchUseCase: IsFavoriteLaunchUseCase,
    private val deleteLaunchUseCase: DeleteLaunchUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private var _state = MutableLiveData(DetailUiState())
    val state: LiveData<DetailUiState>
        get() = _state

    fun isFavoriteLaunch(launch: Launch) {
        viewModelScope.launch {
            isFavoriteLaunchUseCase(launch.flightNumber)
                .flowOn(dispatcher)
                .catch { setFavoriteState(false) }
                .collect { setFavoriteState(it) }
        }
    }

    fun onFavoriteIconClicked(launch: Launch) {
        viewModelScope.launch(dispatcher) {
            if (currentState().isFavorite) {
                deleteLaunchUseCase(launch)
                    .collect { setFavoriteState(false) }
            } else {
                insertFavoriteLaunchUseCase(launch)
                    .collect { setFavoriteState(true) }
            }
        }
    }

    private fun setFavoriteState(isFavorite: Boolean) {
        _state.postValue(DetailUiState(isFavorite))
    }

    private fun currentState() = _state.value!!
}
