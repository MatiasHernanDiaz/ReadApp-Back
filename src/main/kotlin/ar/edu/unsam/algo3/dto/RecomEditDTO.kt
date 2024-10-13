package ar.edu.unsam.algo3.dto


import ar.edu.unsam.algo3.Recomendacion

data class RecomEditDTO(
    val creator: UserToEditDTO,
    val title: String = "",
    var description: String,
    var publicIs: Boolean,
    val id: Int,
)

fun Recomendacion.toEditDTO() =
    RecomEditDTO(id = id, title = titulo, description = resegna,
        publicIs = publica, creator = creador.toEditDTO())

