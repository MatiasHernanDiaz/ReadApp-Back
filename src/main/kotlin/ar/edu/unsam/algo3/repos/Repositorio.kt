package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.*
import ar.edu.unsam.algo3.model.recoms
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Component


abstract class Repositorio<T:ItemRepo> {
    abstract protected val items: MutableSet<T>

    private fun existeItem(item: T): Boolean = items.map { it.id }.contains(item.id)

    fun crearItem(item: T) {
        if (existeItem(item)) {
            throw Exception("El item ya existe en el repositorio.")
        }

        val lastId = items.maxOfOrNull { it.id!! }
        val newId = if(lastId != null) lastId + 1u else 1u
        item.id = newId
        items.add(item)
    }

    fun actualizarItem(item: T) {
        if (!existeItem(item)) {
            throw Exception("No hay un item con ese ID en el repositorio.")
        }

        items.removeIf { it.id == item.id }
        items.add(item)
    }

    fun eliminarItem(item: T) {
        if (!existeItem(item)) {
            throw Exception("No hay un item con ese ID en el repositorio.")
        }
        items.removeIf { it.id == item.id }
    }


    fun itemPorId(id: UInt): T? = items.find { it.id == id }


    abstract fun buscarItems(patron: String): List<T>

    fun ejecutarAccion(accion: AccionRepositorio) {
        accion.ejecutar()
    }

    fun items() = items.toList()

    fun limpiarParaTest() = items.clear()
}

interface ItemRepo {
    var id: UInt?
}

interface ServiceLibros {
    fun getLibros(): String
}