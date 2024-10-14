package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.Valoracion


data class RatingDTO(val rating: Int, val description: String, val creatorId: Int)

fun Valoracion.toDTO() = RatingDTO(
    rating = rating,
    description = description,
    creatorId = autor.id
)

