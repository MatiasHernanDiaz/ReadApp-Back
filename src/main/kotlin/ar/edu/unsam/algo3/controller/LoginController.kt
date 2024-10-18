package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.UserDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.errors.BadCredentailsError
import ar.edu.unsam.algo3.service.LoginService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], methods = [RequestMethod.POST, RequestMethod.GET])
@RequestMapping("/auth")
class LoginController(val loginService: LoginService) {

    @PostMapping("/register")
    fun register() {

    }

    @GetMapping("/login")
    fun getSignedUser(): SignedUserRes {
        val signedUser = loginService.getSignedUser()

        if(signedUser === null) { return SignedUserRes(false, null,"") }

        return SignedUserRes(true, signedUser.toDTO(),"")
    }

//    @PostMapping("/login")
//    fun login(@RequestBody credentials: CredentialsDTO): ResponseEntity<SignedUserRes> {
//        val user = loginService.login(credentials.email, credentials.password)
//
//        return if (user != null) {
//            ResponseEntity.ok(SignedUserRes(true, user.toDTO(),"Login Existoso"))
//        } else {
//            ResponseEntity.status(401).body(SignedUserRes(false, null, "Credenciales Invalidas"))
//        }
//    }
    @PostMapping("/login")
    fun login(@RequestBody credentials: CredentialsDTO): SignedUserRes {
    return try {
        // Intenta loguear al usuario
        val user = loginService.login(credentials.email, credentials.password)
        SignedUserRes(true, user.toDTO(), "Login Exitoso")
    } catch (e: BadCredentailsError) {
        SignedUserRes(false, null, e.message ?: "Credenciales inválidas")
    }
}


    @GetMapping("/logout")
    fun logout(): SignedUserRes {
        loginService.logout()

        return SignedUserRes(false, null, "Usuario deslogueado")
    }
}

data class CredentialsDTO( val email: String, val password: String)
data class SignedUserRes(val login: Boolean, val user: UserDTO?, val mensagge: String)

