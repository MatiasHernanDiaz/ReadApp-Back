package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.ReadMode
import ar.edu.unsam.algo3.SearchCriteria
import ar.edu.unsam.algo3.User
import ar.edu.unsam.algo3.dto.UserDTO
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
            searchCriteria = SearchCriteria.fromCustomString(userDTO.searchCriteria),
            friends = oldUser.friends,
            readBooks = oldUser.readBooks,
            booksToRead = oldUser.booksToRead,
            recommendations = oldUser.recommendations,
            ratings = oldUser.ratings,
            readMode = ReadMode.fromCustomString(userDTO.readMode),
            avatar = userDTO.avatar
        )
    }
}