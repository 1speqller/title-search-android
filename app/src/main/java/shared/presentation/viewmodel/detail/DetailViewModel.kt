package shared.presentation.viewmodel.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shared.domain.interactor.MovieInteractor

class DetailViewModel(
    private val interactor: MovieInteractor
): ViewModel() {
    private val _state = MutableStateFlow<DetailScreenState>(DetailScreenState.Start)
    val state: StateFlow<DetailScreenState> = _state.asStateFlow()

    fun onInit(id: Int) {
        _state.value = DetailScreenState.Loading
        viewModelScope.launch {
            try {
                _state.value = DetailScreenState.Success(interactor.searchDetailMovie(id))
            } catch (e: Exception) {
                _state.value = DetailScreenState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }
}