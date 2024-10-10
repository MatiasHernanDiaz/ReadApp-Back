package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.Libro
import org.springframework.stereotype.Component

@Component
class RepositorioLibros : Repository<Libro>() {
    override val items = mutableSetOf<Libro>()

    override fun searchItems(patron: String) = items.filter { patron.lowercase() in it.titulo().lowercase()
            || patron.lowercase() in it.autor().lastName().lowercase() }
}
