package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.model.recoms
import org.springframework.stereotype.Component

@Component
class RepositorioRecomendaciones : Repositorio<Recomendacion>() {
    override val items = recoms.toMutableSet()
    override fun buscarItems(patron: String) = items.filter {
        patron.lowercase() == it.creador().apellido().lowercase() || patron.lowercase() in it.resegna().lowercase()
                || it.libros().any { l -> patron.lowercase() in l.titulo().lowercase() }
    }
}