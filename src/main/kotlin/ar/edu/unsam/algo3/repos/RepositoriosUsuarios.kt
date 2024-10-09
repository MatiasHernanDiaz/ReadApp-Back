package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.User
import org.springframework.stereotype.Component

@Component
class RepositoriosUsuarios : Repositorio<User>() {
    override val items = mutableSetOf<User>()
    override fun buscarItems(patron: String) = items.filter { patron.lowercase() in it.nombreCompleto().lowercase()
            || patron.lowercase() == it.userName().lowercase() }
}

