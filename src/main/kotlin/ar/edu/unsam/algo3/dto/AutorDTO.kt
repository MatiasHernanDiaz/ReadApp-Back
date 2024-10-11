package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.*

data class AutorDTO(
    val lastName: String,
    var firstName: String,
    val nativeLanguage: Language,
    val id:Int
)
fun Author.toDTO()= AutorDTO(id=id, lastName = lastName, firstName = firstName, nativeLanguage = nativeLanguage)
