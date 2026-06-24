package shared.data.api.model

data class MovieResponse(
    val show: Movie
)
data class Movie (
    val id: Int,
    val name: String,
    val summary: String?,
    val image: ImageModel?,
    val genres: List<String>
)