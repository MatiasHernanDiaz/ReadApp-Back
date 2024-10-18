package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.Recomendacion

data class RecomEditDTO(
    val creator: UserToEditDTO,
    val title: String = "",
    var description: String,
    var publicIs: Boolean,
    val id: Int,
    val ratings: List<RatingDTO>,
    val canRating: Boolean,
    val canEdit: Boolean,
    val books: List<BookDTO>
)

fun Recomendacion.toEditDTO() =
    RecomEditDTO(id = id, title = titulo, description = resegna,
        publicIs = publica, creator = creador.toEditDTO(), ratings = valoraciones.map { it.toDTO() }, canRating = canRating, canEdit = canEdit, books = libros.map { it.toDTO() })

