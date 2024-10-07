package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.Usuario
import ar.edu.unsam.algo3.Valoracion
import ar.edu.unsam.algo3.dto.FriendDTO
import org.apache.catalina.User

data class RatingDTO(val rating: Int, val description: String, val creator: FriendDTO)

fun Valoracion.toDTO() = RatingDTO(rating = puntuacion, description = comentario, creator = FriendDTO(id = autor.id, firstName = autor.nombre, lastName = autor.apellido, username = autor.userName, email = autor.email))

