package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.*
import java.time.LocalDate

data class UserDTO(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String,
    val email: String,
    val birthday: LocalDate,
    var searchCriteria: String,//ojo
    val nativeLanguage: Lenguaje,
    val readTimeMinAvg: Int,
    val friends: List<FriendDTO>,
    val readBooks: List<BookDTO>,
    val readToBooks: List<BookDTO>,
    val readMode: String,//ojo
    val avatar: String,
    val id: Int
)

fun Usuario.toDTO() = UserDTO(
    id = id, firstName = nombre, lastName = apellido,
    username = userName, password = password, email = email,
    birthday = fechaNacimiento, searchCriteria = perfil.toString(),
    nativeLanguage = lenguajeNativo, readTimeMinAvg = palabrasXMinuto,
    friends = amigos.map {
        FriendDTO(
            id = it.id, firstName = it.nombre, lastName = it.apellido,
            username = it.userName, email = it.email
        )
    },
    readBooks = librosLeidos.map { it.toDTO() },
    readToBooks = librosALeer.map { it.toDTO() },
    readMode = tipoLector.toString(),
    avatar = avatar

)

data class FriendDTO(
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val id: Int
)
