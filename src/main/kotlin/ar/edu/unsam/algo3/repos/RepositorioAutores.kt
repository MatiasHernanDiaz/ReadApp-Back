package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.Author
import org.springframework.stereotype.Component

@Component
class RepositorioAutores : Repository<Author>() {
    override val items = mutableSetOf<Author>()

    override fun searchItems(patron: String): List<Author> = items()
}