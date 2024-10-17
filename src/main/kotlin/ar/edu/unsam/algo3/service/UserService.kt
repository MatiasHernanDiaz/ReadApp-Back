package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.*
import ar.edu.unsam.algo3.dto.UserDTO
import ar.edu.unsam.algo3.repos.UserRepository
import org.springframework.stereotype.Service



@Service
class UserService (val userRepo: UserRepository){
    fun getAllUser(): MutableSet<User> {
        return userRepo.items
    }

    fun editProfile(userDTO: UserDTO): User {
        val newUser = userDTOToUser(userDTO)
        userRepo.updateItem(newUser)
        return userRepo.itemById(userDTO.id, "No id de usuario al editar perfil")
    }

    fun userDTOToUser(userDTO: UserDTO): User {
        val oldUser = userRepo.itemById(userDTO.id,"No se encontro el usuario especificado")

        val newSearchCriteria = if(userDTO.searchCriteria.size > 1)
            SearchCriteria.fromCustomString(
                "Combinado",
                user = oldUser,
                profiles = userDTO.searchCriteria
            ) else
            SearchCriteria.fromCustomString(userDTO.searchCriteria[0])


        return User(
            id = userDTO.id,
            firstName = userDTO.firstName,
            lastName = userDTO.lastName,
            username = userDTO.username,
            password = oldUser.password,
            email = userDTO.email,
            birthday = userDTO.birthday,
            nativeLanguage = userDTO.nativeLanguage,
            readTimeMinAvg = userDTO.readTimeMinAvg,
            searchCriteria = newSearchCriteria,
            friends = oldUser.friends,
            readBooks = oldUser.readBooks,
            booksToRead = oldUser.booksToRead,
            recommendations = oldUser.recommendations,
            ratings = oldUser.ratings,
            readMode = ReadMode.fromCustomString(userDTO.readMode),
            avatar = userDTO.avatar
        )
    }

    fun getUser(userid: Int): User {
        return userRepo.itemById(userid, "Usuario no encontrado")
    }

    fun getFriends(userid: Int): List<User> {
        val user = userRepo.itemById(userid, "El id indicado no corresponde a ningún usuario")
        return user.friends.toList()
    }

    fun getBooksToRead(userid: Int, toread: Boolean): List<Libro> {
        if(toread){
            return userRepo.itemById(userid, "Id de usuario no encontrado al buscar libros a leer").booksToRead.toList()
        }
        else{
            return userRepo.itemById(userid, "Id de usuario no encontrado al buscar libros lidos").readBooks.toList()
        }
    }

    fun getCandidatesToFriends(userid: Int, search: String?): List<User> {
        val user = userRepo.itemById(userid, "Id de usuario inexistente")

        val users: List<User>

        if( search !== null ) {
            users = userRepo.searchItems(search)
        } else {
            users = getAllUser().toList()
        }
        return users.filter{ it !== user && !user.friends().contains(it) }
    }

    fun addFriend(userid: Int, friendDTO: UserDTO): List<User> {
        val newFriend = userRepo.itemById(friendDTO.id, "Id de usuario inexistente, al buscar amigos")
        val user = userRepo.itemById(userid, "Id de usuario inexistente")

        user.addFriend(newFriend)

        return user.friends.toList()
    }
}