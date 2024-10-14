package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.service.BookService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], methods = [RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET])
class BookController (val bookService: BookService){

    @GetMapping("/books")
    fun recommendation() = bookService.getAllBook().map { it.toDTO() }

}