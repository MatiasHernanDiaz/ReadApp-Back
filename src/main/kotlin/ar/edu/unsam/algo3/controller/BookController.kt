package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.service.BookService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], methods = [RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET])
class BookController (val bookService: BookService){

    @GetMapping("/books")
    fun recommendation() = bookService.getAllBook().map { it.toDTO() }


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