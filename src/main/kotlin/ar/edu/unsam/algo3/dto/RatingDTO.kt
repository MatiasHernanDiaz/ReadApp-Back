package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.Valoracion
import java.time.LocalDate


data class RatingDTO(
    val rating: Int,
    val description: String,
    val creatorId: Int,
    val creatorFullName: String,
    val createDate: LocalDate,
    val avatar: String)

fun Valoracion.toDTO() = RatingDTO(
    rating = rating,
    description = description,
    creatorId = autor.id,
    creatorFullName = autor.displayName(),
    createDate = createDate,
    avatar = autor.avatar
)

