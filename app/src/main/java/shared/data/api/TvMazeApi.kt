package shared.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import shared.data.api.model.DetailMovieResponseModel
import shared.data.api.model.MovieResponse

interface TvMazeApi {
    @GET("search/shows")
    suspend fun searchMovies(
        @Query("q") query: String
    ): List<MovieResponse>

    @GET("shows/{id}")
    suspend fun searchDetailMovie(
        @Path("id") id: Int
    ) : DetailMovieResponseModel
}