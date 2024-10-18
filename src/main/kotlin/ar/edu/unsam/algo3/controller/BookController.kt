package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.BookDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.service.BookService
import ar.edu.unsam.algo3.service.UserService
import ar.edu.unsam.algo3.dto.*
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], methods = [RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET,RequestMethod.DELETE])
class BookController (val bookService: BookService, val userService: UserService){

    @GetMapping("/books")
    fun books(@RequestParam("text") text: String = "") =
        bookService.getAllBook(text).map { it.toDTO() }

    @GetMapping("/books/nottoread/{userID}")
    fun booksToRead(@PathVariable userID: Int,@RequestParam("text") text: String = "") =
        bookService.getAllBook(text)
            .filter { !userService.getUser(userID).booksToRead().contains(it) }
            .map { it.toDTO() }

    @GetMapping("/books/notread/{userID}")
    fun booksRead(@PathVariable userID: Int,@RequestParam("text") text: String = "") =
        bookService.getAllBook(text)
            .filter { !userService.getUser(userID).readBooks().contains(it) }
            .map { it.toDTO() }


    @GetMapping("/books/{recomid}/candidatestobook")
    fun getCandidatesToBook(
        @PathVariable recomid: Int,
        @RequestParam("userid") userid:Int,
        @RequestParam("search") search: String?
    ): List<BookDTO> = bookService.getCandidatesToBook(recomid, userid, search).map { it.toDTO() }

    @PostMapping("/books/{recomid}/addbook")
    fun addBook(@PathVariable recomid: Int,
                  @RequestParam("userid") userid:Int,
                  @RequestBody bookDTO: BookDTO): RecomEditDTO = bookService.addBook(
        recomid, userid, bookDTO).toEditDTO()
}