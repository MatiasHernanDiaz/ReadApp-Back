package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.Recomendacion

data class RecomDTO(
    val creator: CreatorDTO,
    val title: String = "",
    var description: String,
    val books: List<BookDTO>,
    var publicIs: Boolean,
    val id: Int,
    val ratings: List<RatingDTO>,
    val ratingsAvg: Double
)

fun Recomendacion.toDTO() =
    RecomDTO(id = id, title = titulo, description = resegna,
        books = libros.map { it.toDTO() }, publicIs = publica,
        creator = creador.toCreatorDTO(), ratings = valoraciones.map { it.toDTO() },
        ratingsAvg = promedioValoraciones())

data class CreateRecomDTO(
    val title: String = "",
    val userid: Int
)


