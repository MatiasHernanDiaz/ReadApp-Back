package ar.edu.unsam.algo3

import ar.edu.unsam.algo3.model.libroAutorNoPreferido
import ar.edu.unsam.algo3.repos.RepositorioLibros
import ar.edu.unsam.algo3.repos.RepositoriosUsuarios
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReadApp2024Grupo7

fun main(args: Array<String>) {
    runApplication<ReadApp2024Grupo7>(*args)
    val bookRepositorio: RepositorioLibros = RepositorioLibros()
    bookRepositorio.crearItem(libroAutorNoPreferido)
}
