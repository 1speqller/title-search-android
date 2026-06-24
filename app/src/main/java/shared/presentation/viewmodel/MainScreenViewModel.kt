package shared.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import shared.domain.interactor.MovieInteractor

class MainScreenViewModel(
    private val interactor: MovieInteractor
): ViewModel() {
    private val _state = MutableStateFlow<MainScreenState>(MainScreenState.Start)
    val state: StateFlow<MainScreenState> = _state.asStateFlow()
    private val _query = MutableStateFlow<String?>("")
    val query: StateFlow<String?> = _query.asStateFlow()

    init {
        viewModelScope.launch {
            _query
                .debounce(2000)
                .filter { (it?.length ?: 0) >= 3 }
                .distinctUntilChanged()
                .collectLatest { query ->
                    searchMovies(query ?: "")
                }
        }
    }
    private fun searchMovies(query: String) {
        viewModelScope.launch {
            _state.value = MainScreenState.Loading
            try {
                _state.value = MainScreenState.Success(interactor.searchMovies(query))
            } catch (e: Exception) {
                _state.value = MainScreenState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }

    fun onQueryChange(value: String) {
        _query.value = value
    }
}