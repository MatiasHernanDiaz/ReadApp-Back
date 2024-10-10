package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.*


data class BookDTO(
    val title: String = "",
    val autor: AutorDTO,
    val pages: Int,
    val words: Int,
    val id: Int
) {
    fun toBook(): Libro {
        TODO("Not yet implemented")
    }
}

fun Libro.toEditProfileDTO() = BookDTO(id = id, title = titulo, autor = autor.toEditProfileDTO(), pages = paginas, words = palabras)