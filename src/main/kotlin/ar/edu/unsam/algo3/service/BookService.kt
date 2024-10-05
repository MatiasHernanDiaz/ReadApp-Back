package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Libro
import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.model.libroAutorNoPreferido

import ar.edu.unsam.algo3.repos.RepositorioLibros


import org.springframework.stereotype.Service

@Service
class BookService (val bookRepositorio: RepositorioLibros){
    //Fijate que tenes un quilombo con la funcion actualizar, hay que reemplazarla.
    fun getAllBook(): List<Libro> {
        //bookRepositorio.crearItem(libroAutorNoPreferido)
        return bookRepositorio.items()
    }

}