package shared.domain.interactor

import shared.data.api.model.DetailMovieResponseModel
import shared.data.repository.MovieRepositoryImpl
import shared.domain.model.DetailMovieModel
import shared.domain.model.MovieModel

class MovieInteractor(
    private val repository: MovieRepositoryImpl
) {
    suspend fun searchMovies(query: String): List<MovieModel> {
        return repository.searchMovies(query)
    }

    suspend fun searchDetailMovie(id: Int): DetailMovieModel {
        return repository.searchDetailMovie(id)
    }
}