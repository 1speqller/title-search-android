package shared.data.api.model
data class DetailMovieResponseModel (
    val id: Int,
    val name: String?,
    val status: String,
    val premiered: String?,
    val ended: String?,
    val image: ImageModel,
    val summary: String?,
    val officialSite: String?
)