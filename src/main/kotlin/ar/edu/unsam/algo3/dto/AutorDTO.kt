package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.*

data class AutorDTO(
    val lastName: String,
    var firstName: String,
    val nativeLanguage: Language,
    val id:Int
)
fun Autor.toEditProfileDTO()= AutorDTO(id=id, lastName = apellido, firstName = nombre, nativeLanguage = idiomaNativo)
