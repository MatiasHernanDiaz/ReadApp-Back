package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.*


data class BookDTO(
    val title: String = "",
    val autor: String = "",
    val imageUrl: String,
    val pages: Int,
    val words: Int,
    val id: Int
)

fun Libro.toDTO() = BookDTO(id = id, title = titulo, autor = (autor.toDTO().firstName + " " + autor.toDTO().lastName), imageUrl = imageURL ,pages = paginas, words = palabras)