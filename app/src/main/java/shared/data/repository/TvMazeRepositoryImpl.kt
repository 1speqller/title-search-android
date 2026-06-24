package shared.data.repository

import shared.data.api.TvMazeApi
import shared.data.datasource.RemoteDataSourceImpl
import shared.domain.model.ImageModel
import shared.domain.model.ShowModel
import shared.domain.repository.TvMazeRepository

class TvMazeRepositoryImpl(
    private val dataSource: RemoteDataSourceImpl
): TvMazeRepository {

    override suspend fun searchShows(query: String): List<ShowModel> {
        val response = dataSource.searchShow(query)
        val shows = mutableListOf<ShowModel>()
        response.forEach {
            shows.add(
                ShowModel(
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
        return shows
    }
}