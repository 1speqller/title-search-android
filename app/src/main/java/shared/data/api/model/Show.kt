package shared.data.api.model

data class ShowResponse(
    val show: Show
)
data class Show (
    val id: Int,
    val name: String,
    val summary: String?,
    val image: Image?,
    val genres: List<String>
)