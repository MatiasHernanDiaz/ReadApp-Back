package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.User
import ar.edu.unsam.algo3.errors.BadCredentailsError
import ar.edu.unsam.algo3.errors.NoIdException
import org.springframework.stereotype.Service
import kotlin.math.sign

@Service
class LoginService(val userService: UserService) {
    private var signedUser: User? = null

    fun getSignedUser(): User? = signedUser

    fun refreshSignedUser(user: User) {
        signedUser = user
    }

    fun login(email: String, password: String): User {
        val allUsers = userService.getAllUser()
        val userByEmail = allUsers.find { us -> us.email == email }

        if(userByEmail === null) {
            throw BadCredentailsError("Credenciales inválidas")
        }

        if(userByEmail.password != password) {
            throw BadCredentailsError("Credenciales inválidas")
        }
        signedUser = userByEmail

        return userByEmail
    }

    fun logout(): User? {
        signedUser = null
        return signedUser
    }
}

