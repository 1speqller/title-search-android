package shared.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import shared.domain.interactor.MovieInteractorImpl
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class)
class MainScreenViewModel(
    private val interactor: MovieInteractorImpl
): ViewModel() {
    private val _state = MutableStateFlow<MainScreenState>(MainScreenState.Start)
    val state: StateFlow<MainScreenState> = _state.asStateFlow()
    private val _eventFlow = MutableSharedFlow<MainUIEvent>()
    val eventFlow: SharedFlow<MainUIEvent> = _eventFlow.asSharedFlow()
    private val _query = MutableStateFlow<String?>("")
    val query: StateFlow<String?> = _query.asStateFlow()

    init {
        viewModelScope.launch {
            _query
                .debounce(2000.milliseconds)
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
                _eventFlow.emit(MainUIEvent.ShowError("Ошибка загрузки: ${e.message}"))
            }
        }
    }

    fun onQueryChange(value: String) {
        _query.value = value
    }
}