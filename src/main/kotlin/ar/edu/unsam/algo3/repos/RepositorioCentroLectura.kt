package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.CentroLectura

class RepositorioCentroLectura : Repositorio<CentroLectura>() {
    override val items = mutableSetOf<CentroLectura>()
    override fun buscarItems(patron: String) = items.filter { patron.lowercase() == it.libro.titulo().lowercase() }
}