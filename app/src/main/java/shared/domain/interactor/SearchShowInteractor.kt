package shared.domain.interactor

import shared.data.api.model.Show
import shared.data.api.model.ShowResponse
import shared.data.repository.TvMazeRepositoryImpl
import shared.domain.model.ShowModel

class SearchShowInteractor(
    private val repository: TvMazeRepositoryImpl
) {

    suspend fun searchShows(query: String): List<ShowModel> {
        return repository.searchShows(query)
    }
}