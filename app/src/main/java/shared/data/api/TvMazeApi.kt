package shared.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import shared.data.api.model.Show
import shared.data.api.model.ShowResponse

interface TvMazeApi {
    @GET("search/shows")
    suspend fun searchShows(
        @Query("q") query: String
    ): List<ShowResponse>
}