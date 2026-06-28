package shared.presentation.viewmodel.main

sealed interface MainUIEvent {
    data class ShowError(val message: String): MainUIEvent
}