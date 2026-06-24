package shared.domain.model

data class MovieModel (
    val id: Int,
    val name: String = "Без названия",
    val summary: String? = "Без описания",
    val image: ImageModel?,
    val genres: List<String>? = emptyList()
)
