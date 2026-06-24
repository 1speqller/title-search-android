package shared.data.datasource

import shared.data.api.model.DetailMovieResponseModel
import shared.data.api.model.MovieResponse

interface RemoteDataSource {

    suspend fun searchMovies(query: String): List<MovieResponse>
    suspend fun searchDetailMovie(id: Int): DetailMovieResponseModel

}