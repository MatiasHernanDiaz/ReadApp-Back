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

fun Libro.toDTO() = BookDTO(id = id, title = titulo, autor = autor.toDTO(), pages = paginas, words = palabras)