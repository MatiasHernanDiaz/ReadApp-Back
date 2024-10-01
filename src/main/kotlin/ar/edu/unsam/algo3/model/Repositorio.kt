package ar.edu.unsam.algo3

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


abstract class Repositorio<T:ItemRepo> {
    protected val items: MutableSet<T> = mutableSetOf()

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

    fun items() = items

    fun limpiarParaTest() = items.clear()
}

object repositorioLibros : Repositorio<Libro>() {
    lateinit var service: ServiceLibros
    override fun buscarItems(patron: String) = items.filter { patron.lowercase() in it.titulo().lowercase()
            || patron.lowercase() in it.autor().apellido().lowercase() }

    @Serializable
    data class LibroPayload(
        val id: UInt,
        val ediciones: Int,
        val ventasSemanales: Int
    )

    fun actualizarLibros() {
        val payload = Json.decodeFromString<List<LibroPayload>>(service.getLibros())

        payload.forEach { pay ->
            val libro = items.find { it.id == pay.id }

            if (libro != null) {
                libro.editarEdiciones(pay.ediciones)
                libro.editarVentasSemanales(pay.ventasSemanales)
            } else {
                throw Exception("El payload incluía una referencia a un ID inexistente.")
            }
        }
    }
}

object repositorioUsuarios : Repositorio<Usuario>() {
    override fun buscarItems(patron: String) = items.filter { patron.lowercase() in it.nombreCompleto().lowercase()
            || patron.lowercase() == it.userName().lowercase() }
}

object repositorioAutores : Repositorio<Autor>() {
    override fun buscarItems(patron: String) = items.filter {
        patron.lowercase() in it.nombre().lowercase() || patron.lowercase() in it.apellido().lowercase()
                || patron.lowercase() == it.seudonimo().lowercase()
    }
}

object repositorioRecomendaciones : Repositorio<Recomendacion>() {
    override fun buscarItems(patron: String) = items.filter {
        patron.lowercase() == it.creador().apellido().lowercase() || patron.lowercase() in it.resegna().lowercase()
                || it.libros().any { l -> patron.lowercase() in l.titulo().lowercase() }
    }
}

object repositorioCentroLectura : Repositorio<CentroLectura>() {
    override fun buscarItems(patron: String) = items.filter { patron.lowercase() == it.libro.titulo().lowercase() }
}

interface ItemRepo {
    var id: UInt?
}

interface ServiceLibros {
    fun getLibros(): String
}