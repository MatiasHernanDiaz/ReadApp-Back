package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.*
import java.time.LocalDate

data class UserDTO(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String? = null,
    val email: String,
    val birthday: LocalDate,
    var searchCriteria: String,//ojo
    val nativeLanguage: Lenguaje,
    val readTimeMinAvg: Int,
//    val friends: List<FriendDTO>,
//    val readBooks: List<BookDTO>,
//    val readToBooks: List<BookDTO>,
//    val readMode: String,//ojo
//    val avatar: String,
    val id: Int
) {
    fun toUser(): User {

        return User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            username = username,
            password = password,
            email = email,
            birthday = birthday,
            nativeLanguage = nativeLanguage,
            readTimeMinAvg = readTimeMinAvg,
            searchCriteria = SearchCriteria.fromCustomString(searchCriteria),
//            friends = friends.map{ it.toUser() }.toMutableSet(),
//            readBooks = readBooks.map { it.toBook() }.toMutableList(),
//            readToBooks = readToBooks.map { it.toBook() }.toMutableSet(),
//            recommendations = ,
//            ratings = ,
//            readMode = ,
//            avatar = userDTO.avatar
        )
    }
}

fun User.toDTO() = UserDTO(
    id = id,
    firstName = firstName,
    lastName = lastName,
    username = username,
    email = email,
    birthday = birthday,
    searchCriteria = searchCriteria.toCustomString(),
    nativeLanguage = nativeLanguage, readTimeMinAvg = readTimeMinAvg,
//    friends = friends.map {
//        FriendDTO(
//            id = it.id, firstName = it.firstName, lastName = it.lastName,
//            username = it.username, email = it.email
//        )
//    },
//    readBooks = readBooks.map { it.toDTO() },
//    readToBooks = readToBooks.map { it.toDTO() },
//    readMode = readMode.toString(),
//    avatar = avatar

)

data class FriendDTO(
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val id: Int
) {
    fun toUser(): User {
        TODO("Not yet implemented")
    }
}
