package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.Libro
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Component

@Component
class RepositorioLibros : Repositorio<Libro>() {
    override val items = mutableSetOf<Libro>()
    lateinit var service: ServiceLibros
    override fun buscarItems(patron: String) = items.filter { patron.lowercase() in it.titulo().lowercase()
            || patron.lowercase() in it.autor().apellido().lowercase() }

//    @Serializable
//    data class LibroPayload(
//        val id: Int,
//        val ediciones: Int,
//        val ventasSemanales: Int
//    )

   fun actualizarLibros() {
//        val payload = Json.decodeFromString<List<LibroPayload>>(service.getLibros())
//
//        payload.forEach { pay ->
//            val libro = items.find { it.id == pay.id }
//
//            if (libro != null) {
//                libro.editarEdiciones(pay.ediciones)
//                libro.editarVentasSemanales(pay.ventasSemanales)
//            } else {
//                throw Exception("El payload incluía una referencia a un ID inexistente.")
//            }
//        }
    }
}
