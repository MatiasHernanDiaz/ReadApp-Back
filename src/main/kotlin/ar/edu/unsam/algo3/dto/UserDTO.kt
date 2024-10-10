package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.*
import java.time.LocalDate

data class UserDTO(
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val birthday: LocalDate,
    var searchCriteria: String,//ojo
    val nativeLanguage: Language,
    val readTimeMinAvg: Int,
    val avatar: String,
    val readMode: String,
    val id: Int,
//    val friends: List<FriendDTO>,
//    val readBooks: List<BookDTO>,
//    val readToBooks: List<BookDTO>,
//    val ratings
)

fun User.toEditProfileDTO() = UserDTO(
    id = id,
    firstName = firstName,
    lastName = lastName,
    username = username,
    email = email,
    birthday = birthday,
    searchCriteria = searchCriteria.toCustomString(),
    nativeLanguage = nativeLanguage,
    readTimeMinAvg = readTimeMinAvg,
    readMode = readMode.toCustomString(),
    avatar = avatar
)

