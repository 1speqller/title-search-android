package shared.presentation.viewmodel

import shared.domain.model.ShowModel

sealed interface MainScreenState {
    object Start: MainScreenState
    object Loading: MainScreenState
    data class Error(val message: String): MainScreenState
    data class Success(val shows: List<ShowModel>): MainScreenState
}