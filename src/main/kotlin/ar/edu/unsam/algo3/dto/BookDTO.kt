package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.*
import java.util.Date


data class BookDTO(
    val title: String = "",
    val autor: String,
    val imageURL: String,
    val date: Date,
    val sales: Int,
    val pages: Int,
    val words: Int,
    val id: Int,
    val lenguages: String
)
{
    fun toBook(): Libro {
        TODO("Not yet implemented")
    }
}

fun Libro.toDTO() = BookDTO(id = id, title = titulo, autor = (autor.toDTO().lastName + " " + autor.toDTO().firstName), imageURL = imagenURL, sales = ventasSemanales ,pages = paginas, date = Date(1969,1,1), words = palabras, lenguages = this.lenguages())

fun Libro.lenguages(): String{
    var response: String = ""
    for(lenguaje in traducciones){
        response = (response + " " + lenguaje.toString())
    }
    return response
}