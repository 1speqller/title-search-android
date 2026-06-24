package shared.data.datasource

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shared.data.api.TvMazeApi
import shared.data.api.model.DetailMovieResponseModel
import shared.data.api.model.MovieResponse

class RemoteDataSourceImpl: RemoteDataSource {
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    private val api: TvMazeApi by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvMazeApi::class.java)
    }
    override suspend fun searchMovies(query: String): List<MovieResponse> {
        return api.searchMovies(query)
    }

    override suspend fun searchDetailMovie(id: Int): DetailMovieResponseModel {
        return api.searchDetailMovie(id)
    }
}