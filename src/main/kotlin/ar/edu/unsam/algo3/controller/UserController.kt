package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController (val userService: UserService){

    @GetMapping("/users")
    fun recommendation() = userService.getAllUser()

}