package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.User
import ar.edu.unsam.algo3.dto.UserDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.service.LoginService
import com.fasterxml.jackson.annotation.JsonFilter
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], methods = [RequestMethod.POST, RequestMethod.GET])
@RequestMapping("/login")
class LoginController(val loginService: LoginService) {

    @PostMapping("/register")
    fun register() {

    }

    @GetMapping("")
    fun getSignedUser(): SignedUserRes {
        val signedUser = loginService.getSignedUser()

        if(signedUser === null) { return SignedUserRes(false, null) }

        return SignedUserRes(true, signedUser.toDTO())
    }

    @PostMapping("")
    fun login(@RequestBody credentials: CredentialsDTO) = loginService.login(credentials.email, credentials.password).toDTO()

}

data class CredentialsDTO( val email: String, val password: String)
data class SignedUserRes(val login: Boolean, val user: UserDTO?)

