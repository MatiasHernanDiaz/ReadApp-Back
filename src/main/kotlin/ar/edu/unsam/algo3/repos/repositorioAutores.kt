package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.Autor

object repositorioAutores : Repositorio<Autor>() {
    override val items = mutableSetOf<Autor>()
    override fun buscarItems(patron: String) = items.filter {
        patron.lowercase() in it.nombre().lowercase() || patron.lowercase() in it.apellido().lowercase()
                || patron.lowercase() == it.seudonimo().lowercase()
    }
}