package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.Recomendacion

data class RecomDTO(
    val creator: UserDTO,
    val title: String = "",
    var description: String,
    val books: List<BookDTO>,
    var publicIs: Boolean,
    val id: Int,
    val ratings: List<RatingDTO>
)

fun Recomendacion.toDTO() =
    RecomDTO(id = id, title = titulo, description = resegna,
        books = libros.map { it.toDTO() }, publicIs = publica,
        creator = creador.toDTO(), ratings = valoraciones.map { it.toDTO() })

