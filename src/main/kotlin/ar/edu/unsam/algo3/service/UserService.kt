package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.*
import ar.edu.unsam.algo3.dto.RecomDTO
import ar.edu.unsam.algo3.dto.UserDTO
import ar.edu.unsam.algo3.errors.NoIdException
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones
import ar.edu.unsam.algo3.repos.UserRepository
import org.springframework.stereotype.Service



@Service
class UserService (val userRepo: UserRepository , val recomRepo:RepositorioRecomendaciones){
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
                minTime = userDTO.minTime,
                maxTime = userDTO.maxTime,
                profiles = userDTO.searchCriteria
            ) else
            SearchCriteria.fromCustomString(
                userDTO.searchCriteria[0],
                user = oldUser,
                minTime = userDTO.minTime,
                maxTime = userDTO.maxTime
            )


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
    fun deleteFriend(userid: Int, friendId: Int): List<User> {
        val user = userRepo.itemById(userid)
        val friendToRemove = userRepo.itemById(friendId)  // Asumiendo que los amigos también son usuarios

        if (user === null || friendToRemove === null) {
            throw NoIdException("Id de usuario o amigo inexistente")
        }
        user.deleteFriend(friendToRemove)
        return user.friends.toList()
    }

    fun addFavoriteRecom(userId: Int, recomId: Int): List<Recomendacion> {
        val user = userRepo.itemById(userId)
        val recommendation = recomRepo.itemById(recomId)
        user.addFavoriteRecom(recommendation)
        return user.favoriteRecoms.toList()
    }

    fun removeFavoriteRecom(userId: Int, recomId: Int): List<Recomendacion> {
        val user = userRepo.itemById(userId)
        val recommendation = recomRepo.itemById(recomId)
        user.removeFavoriteRecom(recommendation)
        return user.favoriteRecoms.toList()
    }

    fun getFavoriteRecom(userId: Int): List<Recomendacion> {
        val user = userRepo.itemById(userId)
        return user.favoriteRecoms.toList()
    }
}