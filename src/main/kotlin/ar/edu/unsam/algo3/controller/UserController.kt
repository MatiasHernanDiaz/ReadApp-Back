package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.User
import ar.edu.unsam.algo3.dto.UserDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.errors.NoIdException
import ar.edu.unsam.algo3.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController (val userService: UserService){


    @GetMapping("/users")
    fun users() = userService.getAllUser().map { it.toDTO() }


    @PutMapping("/users/editprofile")
    fun editProfile(@RequestBody body: UserDTO) {
        val updatedUser = userService.editProfile(body.toUser())

        if( updatedUser === null ) {
            throw NoIdException( "El usuario enviado no tiene un ID existente." )
        }

        //return "Usuario ${ updatedUser.id } fue actualizado correctamente"
    }
}