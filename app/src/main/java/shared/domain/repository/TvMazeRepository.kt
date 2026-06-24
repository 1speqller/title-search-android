package shared.domain.repository

import shared.data.api.model.Show
import shared.data.api.model.ShowResponse
import shared.domain.model.ShowModel

interface TvMazeRepository {
    suspend fun searchShows(query: String): List<ShowModel>
}