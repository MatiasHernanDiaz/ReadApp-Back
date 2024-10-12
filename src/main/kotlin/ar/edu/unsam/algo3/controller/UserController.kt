package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.UserDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.errors.NoIdException
import ar.edu.unsam.algo3.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], methods = [RequestMethod.PUT])
@RequestMapping("/users")
class UserController (val userService: UserService){


    @GetMapping("")
    fun users() = userService.getAllUser().map { it.toDTO() }


    @PutMapping("editprofile")
    fun editProfile(@RequestBody body: UserDTO): UserDTO {
        val updatedUser = userService.editProfile(body)

        if( updatedUser === null ) {
            throw NoIdException( "El usuario enviado no tiene un ID existente." )
        }

        return updatedUser.toDTO()
    }
}