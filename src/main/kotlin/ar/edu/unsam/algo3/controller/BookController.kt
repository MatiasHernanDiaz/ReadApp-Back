package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.service.BookService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], methods = [RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET])
class BookController (val bookService: BookService){

    @GetMapping("/books")
    fun books(@RequestParam("text") text: String = "") =
        bookService.getAllBook(text).map { it.toDTO() }

}