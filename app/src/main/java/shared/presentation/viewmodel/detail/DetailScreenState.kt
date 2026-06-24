package shared.presentation.viewmodel.detail

import shared.domain.model.DetailMovieModel
import shared.domain.model.MovieModel

sealed interface DetailScreenState {
    object Start: DetailScreenState
    object Loading: DetailScreenState
    data class Error(val message: String): DetailScreenState
    data class Success(val movie: DetailMovieModel): DetailScreenState
}