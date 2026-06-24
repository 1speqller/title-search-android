package shared.data.repository

import shared.data.api.model.Movie
import shared.data.datasource.RemoteDataSourceImpl
import shared.domain.model.DetailMovieModel
import shared.domain.model.ImageModel
import shared.domain.model.MovieModel
import shared.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val dataSource: RemoteDataSourceImpl
): MovieRepository {

    override suspend fun searchMovies(query: String): List<MovieModel> {
        val response = dataSource.searchMovies(query)
        val movie = mutableListOf<MovieModel>()
        response.forEach {
            movie.add(
                MovieModel(
                    id = it.show.id,
                    name = it.show.name,
                    summary = it.show.summary,
                    image = ImageModel(
                        medium = it.show.image?.medium,
                        original = it.show.image?.original
                    ),
                    genres = it.show.genres
                )
            )
        }
        return movie
    }

    override suspend fun searchDetailMovie(id: Int): DetailMovieModel {
        val response = dataSource.searchDetailMovie(id)
        val detailMovie = DetailMovieModel(
            id = response.id,
            name = response.name,
            status = response.status,
            startAt = response.premiered,
            endAt = response.ended,
            image = response.image,
            description = response.summary?.replace(Regex("<.*?>"), "")?.trim(),
            officialSite = response.officialSite
        )
        return detailMovie
    }

}