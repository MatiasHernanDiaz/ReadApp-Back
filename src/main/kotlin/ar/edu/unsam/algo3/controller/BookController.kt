package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController (val bookService: BookService){

    @GetMapping("/books")
    fun recommendation() = bookService.getAllBook()

}