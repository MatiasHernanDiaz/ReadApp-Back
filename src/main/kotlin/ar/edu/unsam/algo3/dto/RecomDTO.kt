package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.Recomendacion

data class RecomDTO(
    val creator: UserDTO,
    val title: String = "",
    var description: String,
    val books: List<BookDTO>,
    var isPublic: Boolean = false,
    val id: Int
)

fun Recomendacion.toEditProfileDTO() =
    RecomDTO(id = id, title = titulo, description = resegna, books = libros.map { it.toEditProfileDTO() }, isPublic = publica, creator = creador.toEditProfileDTO())