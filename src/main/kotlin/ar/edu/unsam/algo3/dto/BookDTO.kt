package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.*


data class BookDTO(
    val title: String = "",
    val autor: String,
    val imageURL: String,
    val pages: Int,
    val words: Int,
    val id: Int
)

fun Libro.toDTO() = BookDTO(id = id, title = titulo, autor = (autor.toDTO().lastName + " " + autor.toDTO().firstName), imageURL = imagenURL, pages = paginas, words = palabras)