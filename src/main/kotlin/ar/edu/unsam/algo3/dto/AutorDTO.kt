package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.*

data class AutorDTO(
    val lastName: String,
    var firstName: String,
    val nativeLanguage: Lenguaje,
    val id:Int
)
fun Autor.toDTO()= AutorDTO(id=id, lastName = apellido, firstName = nombre, nativeLanguage = idiomaNativo)
