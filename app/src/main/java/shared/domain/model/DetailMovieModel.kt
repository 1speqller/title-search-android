package shared.domain.model


data class DetailMovieModel (
    val id: Int,
    val name: String? = "Без названия",
    val status: String? = "Незвестно",
    val startAt: String?,
    val endAt: String?,
    val image: ImageModel?,
    val description: String? = "Без описания",
    val officialSite: String? = "Без официального сайта"
)