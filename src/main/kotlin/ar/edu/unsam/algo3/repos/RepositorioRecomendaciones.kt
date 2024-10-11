package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.Recomendacion
import org.springframework.stereotype.Component

@Component
class RepositorioRecomendaciones : Repository<Recomendacion>() {
    override val items = mutableSetOf<Recomendacion>()

    override fun searchItems(patron: String) = items.filter {
        patron.lowercase() == it.creador().lastName().lowercase() || patron.lowercase() in it.resegna().lowercase()
                || it.libros().any { l -> patron.lowercase() in l.titulo().lowercase() || patron.lowercase() in l.autor.lastName().lowercase() }
    }

}