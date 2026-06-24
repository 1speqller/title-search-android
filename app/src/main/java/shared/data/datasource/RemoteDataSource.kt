package shared.data.datasource

import shared.data.api.model.ShowResponse

interface RemoteDataSource {
    suspend fun searchShow(query: String): List<ShowResponse>
}