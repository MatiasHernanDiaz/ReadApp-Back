package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.Usuario
import org.springframework.stereotype.Component

@Component
class RepositoriosUsuarios : Repositorio<Usuario>() {
    override val items = mutableSetOf<Usuario>()
    override fun buscarItems(patron: String) = items.filter { patron.lowercase() in it.nombreCompleto().lowercase()
            || patron.lowercase() == it.userName().lowercase() }
}

