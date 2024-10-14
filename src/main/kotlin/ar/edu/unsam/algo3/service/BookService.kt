package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Libro
import ar.edu.unsam.algo3.Recomendacion

import ar.edu.unsam.algo3.repos.RepositorioLibros
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.BeanFactoryPostProcessor


import org.springframework.stereotype.Service

@Service
class BookService (
    val bookRepositorio: RepositorioLibros
){

    fun getAllBook(): List<Libro> =
        bookRepositorio.items()

    fun getAllBook(text: String = ""): List<Libro> =
        bookRepositorio.searchItems(text)

}