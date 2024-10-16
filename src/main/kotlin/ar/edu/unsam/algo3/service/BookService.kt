package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Libro
import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.dto.BookDTO
import ar.edu.unsam.algo3.dto.RecomEditDTO
import ar.edu.unsam.algo3.errors.NoIdException

import ar.edu.unsam.algo3.repos.RepositorioLibros
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones
import ar.edu.unsam.algo3.repos.UserRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.BeanFactoryPostProcessor


import org.springframework.stereotype.Service

@Service
class BookService (
    val bookRepositorio: RepositorioLibros,
    val recomRepository: RepositorioRecomendaciones,
    val userRepository: UserRepository
){

    fun getAllBook(): List<Libro> =
        bookRepositorio.items()

    fun getAllBook(text: String = ""): List<Libro> =
        bookRepositorio.searchItems(text)

    fun getCandidatesToBook(recomid: Int, userid: Int, search: String?): List<Libro> {
        val recom = recomRepository.itemById(recomid)
        val user = userRepository.itemById(userid)

        if(recom == null){
            throw NoIdException("Recomendacion no encontrada")
        }
        if(user == null){
            throw NoIdException("Usuario no encontrada")
        }
        if(search != null){
           return recom.getBookToRecom(user).filter { search in it.titulo }
        }

        return recom.getBookToRecom(user)
    }

    fun addBook(recomid: Int, userid: Int, bookDTO: BookDTO): Recomendacion {
        val recom = recomRepository.itemById(recomid)
        val user = userRepository.itemById(userid)
        val book = bookRepositorio.itemById(bookDTO.id)
        if(recom == null){
            throw NoIdException("No se encontro recomendacion")
        }
        if(user == null){
            throw NoIdException("No se encontro usuario logeado")
        }
        if(book == null){
            throw NoIdException("No se encontro libro")
        }
        recom.agregarLibro(user, book)
        return recom
    }

}