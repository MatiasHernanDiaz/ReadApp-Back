package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.Recomendacion
import org.springframework.stereotype.Component

@Component
class RepositorioRecomendaciones : Repositorio<Recomendacion>() {
    override val items = mutableSetOf<Recomendacion>()
    override fun buscarItems(patron: String) = items.filter {
        patron.lowercase() == it.creador().apellido().lowercase() || patron.lowercase() in it.resegna().lowercase()
                || it.libros().any { l -> patron.lowercase() in l.titulo().lowercase() }
    }
}