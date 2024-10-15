package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.*
import ar.edu.unsam.algo3.dto.UserDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.errors.NoIdException
import ar.edu.unsam.algo3.repos.UserRepository
import org.springframework.stereotype.Service



@Service
class UserService (val userRepo: UserRepository){
    fun getAllUser(): MutableSet<User> {
        //userRepo.crearItem(creadorRecom)
        return userRepo.items
    }

    fun editProfile(userDTO: UserDTO): User? {
        val newUser = userDTOToUser(userDTO)
        userRepo.updateItem(newUser)

        return userRepo.itemById(userDTO.id)
    }

    fun userDTOToUser(userDTO: UserDTO): User {
        val oldUser = userRepo.itemById(userDTO.id)

        if(oldUser === null) {
            throw NoIdException("No se encontró el usuario especificado")
        }

        val newSearchCriteria = if(userDTO.searchCriteria.size > 1)
            SearchCriteria.fromCustomString("Combinado", profiles = userDTO.searchCriteria) else
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
        return userRepo.itemById(userid)!!
    }

    fun getFriends(userid: Int): List<User> {
        val user = userRepo.itemById(userid)

        if(user === null) {
            throw NoIdException("El id indicado no corresponde a ningún usuario")
        }

        return user.friends.toList()
    }

    fun getBooksToRead(userid: Int, toread: Boolean): List<Libro> {
        if(toread){
            return userRepo.itemById(userid)!!.booksToRead.toList()
        }
        else{
            return userRepo.itemById(userid)!!.readBooks.toList()
        }
    }

    fun getCandidatesToFriends(userid: Int, search: String?): List<User> {
        val user = userRepo.itemById(userid)

        if(user === null) {
            throw NoIdException("Id de usuario inexistente")
        }

        var users: List<User>

        if( search !== null ) {
            users = userRepo.searchItems(search)
        } else {
            users = getAllUser().toList()
        }
        return users.filter{ it !== user && !user.friends().contains(it) }
    }

    fun addFriend(userid: Int, friendDTO: UserDTO): List<User> {
        val newFriend = userRepo.itemById(friendDTO.id)
        val user = userRepo.itemById(userid)

        if(user === null || newFriend === null) {
            throw NoIdException("Id de usuario inexistente")
        }

        user.addFriend(newFriend)

        return user.friends.toList()
    }
}