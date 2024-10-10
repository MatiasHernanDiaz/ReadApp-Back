package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.Autor
import org.springframework.stereotype.Component

@Component
class RepositorioAutores : Repository<Autor>() {
    override val items = mutableSetOf<Autor>()

    override fun searchItems(patron: String): List<Autor> = items()
    //Hay que arreglar este filtro tmb
//    override fun buscarItems(patron: String) = items.filter {
//        patron.lowercase() in it.nombre().lowercase() || patron.lowercase() in it.apellido().lowercase()
//                || patron.lowercase() == it.seudonimo().lowercase()
//    }
}