package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.Libro
import ar.edu.unsam.algo3.dto.BookDTO
import ar.edu.unsam.algo3.dto.UserDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.service.LoginService
import ar.edu.unsam.algo3.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], methods = [RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE])
@RequestMapping("/users")
class UserController(
    val userService: UserService,
    private val loginService: LoginService
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
    @PostMapping("/{userid}/addtoread")
    fun addToRead(@PathVariable userid: Int, @RequestBody bookDTO: BookDTO): List<BookDTO> = userService.addBookToRead(
        userid, bookDTO).map { it.toDTO() }
    @PostMapping("/{userid}/addreadbook")
    fun addReadBook(@PathVariable userid: Int, @RequestBody bookDTO: BookDTO): List<BookDTO> = userService.addReadBooks(
        userid, bookDTO).map { it.toDTO() }
    @DeleteMapping("/{userid}/deltoread")
    fun deleteBookToRead(@PathVariable userid: Int,@RequestParam("bookid") bookID: Int) {
        userService.delBookToRead(userid, bookID)
    }
    @DeleteMapping("/{userid}/delreadbook")
    fun deleteBookRead(@PathVariable userid: Int,@RequestParam("bookid") bookID: Int) {
        userService.delReadBooks(userid, bookID)
    }

    @GetMapping("/{userid}/bookstoread")
    fun getBooksToRead(@PathVariable userid: Int, @RequestParam("toread") toread: Boolean) = userService.getBooksToRead(userid, toread).map { it.toDTO() }
}