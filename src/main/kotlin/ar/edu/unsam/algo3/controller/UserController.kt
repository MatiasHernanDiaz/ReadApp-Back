package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.RecomDTO
import ar.edu.unsam.algo3.dto.UserDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.service.LoginService
import ar.edu.unsam.algo3.service.RecomService
import ar.edu.unsam.algo3.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], methods = [RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE])
@RequestMapping("/users")
class UserController(
    val userService: UserService,
    private val loginService: LoginService,
    private val recomService: RecomService
){

    @GetMapping("")
    fun users() = userService.getAllUser().map { it.toDTO() }

    @PutMapping("editprofile")
    fun editProfile(@RequestBody body: UserDTO): UserDTO {
        val updatedUser = userService.editProfile(body)
        loginService.refreshSignedUser(updatedUser)
        return updatedUser.toDTO()
    }

    @GetMapping("/{userid}")
    fun users(@PathVariable userid: Int ) = userService.getUser(userid).toDTO()

    @GetMapping("/{userid}/friends")
    fun getFriends(@PathVariable userid: Int) = userService.getFriends(userid).map { it.toDTO() }

    @GetMapping("/{userid}/candidatestofriend")
    fun getCandidatesToFriend(@PathVariable userid: Int, @RequestParam("search") search: String?) = userService.getCandidatesToFriends(
        userid, search).map { it.toDTO() }

    @PostMapping("/{userid}/addfriend")
    fun addFriend(@PathVariable userid: Int, @RequestBody friendDTO: UserDTO): List<UserDTO> = userService.addFriend(
        userid, friendDTO).map { it.toDTO() }

    @GetMapping("/{userid}/bookstoread")
    fun getBooksToRead(@PathVariable userid: Int, @RequestParam("toread") toread: Boolean) = userService.getBooksToRead(userid, toread).map { it.toDTO() }

    @PostMapping("/favorites/{userId}/{recomId}")
    fun addFavoriteRecom(@PathVariable userId: Int, @PathVariable recomId: Int) : List<RecomDTO> = userService.addFavoriteRecom(userId,recomId).map { it.toDTO() }

    @DeleteMapping("/favorites/{userId}/{recomId}")
    fun removeFavoriteRecom(@PathVariable userId: Int, @PathVariable recomId: Int): List<RecomDTO> = userService.removeFavoriteRecom(userId,recomId).map { it.toDTO() }

    @GetMapping("/favorites/{userId}")
    fun getFavorites (@PathVariable userId: Int): List<RecomDTO> = userService.getFavoriteRecom(userId).map { it.toDTO() }

}