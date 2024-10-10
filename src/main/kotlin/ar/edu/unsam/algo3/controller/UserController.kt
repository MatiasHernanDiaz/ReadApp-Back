package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.UserDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.errors.NoIdException
import ar.edu.unsam.algo3.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController (val userService: UserService){


    @GetMapping("")
    fun users() = userService.getAllUser().map { it.toDTO() }


    @PutMapping("editprofile")
    fun editProfile(@RequestBody body: UserDTO): String {
        val updatedUser = userService.editProfile(body)

        if( updatedUser === null ) {
            throw NoIdException( "El usuario enviado no tiene un ID existente." )
        }

        return "Usuario ${ updatedUser.id } fue actualizado correctamente"
    }
}