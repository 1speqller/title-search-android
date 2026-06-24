package shared.data.datasource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shared.data.api.TvMazeApi
import shared.data.api.model.ShowResponse

class RemoteDataSourceImpl: RemoteDataSource {
    private val api: TvMazeApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvMazeApi::class.java)
    }
    override suspend fun searchShow(query: String): List<ShowResponse> {

        return api.searchShows(query)
    }
}