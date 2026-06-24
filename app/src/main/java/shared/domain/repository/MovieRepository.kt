package shared.domain.repository

import shared.data.api.model.DetailMovieResponseModel
import shared.domain.model.DetailMovieModel
import shared.domain.model.MovieModel

interface MovieRepository {
    suspend fun searchMovies(query: String): List<MovieModel>
    suspend fun searchDetailMovie(id: Int): DetailMovieModel
}