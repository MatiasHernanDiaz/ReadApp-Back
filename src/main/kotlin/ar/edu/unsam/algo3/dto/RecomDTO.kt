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

fun Recomendacion.toDTO() =
    RecomDTO(id = id, title = titulo, description = resegna, books = libros.map { it.toDTO() }, isPublic = publica, creator = creador.toDTO())