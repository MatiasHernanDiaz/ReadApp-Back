package ar.edu.unsam.algo3
import ar.edu.unsam.algo3.repos.ItemRepo
import java.time.LocalDate
import java.time.Period


class Autor (
    val apellido: String,
    var nombre: String,
    val seudonimo: String,
    val fechaNacimiento : LocalDate,
    val idiomaNativo: Language,
    val premios : MutableList<Premio> = mutableListOf()
) : ItemRepo {
    override var id: Int = -1

    fun apellido() = apellido
    fun nombre() = nombre
    fun seudonimo() = seudonimo
    fun idiomaNativo(): Language = idiomaNativo
    fun edad(): Int = Period.between(fechaNacimiento, LocalDate.now()).years
    fun esConsagrado(): Boolean = premios.isNotEmpty() or (edad() >= 50)

    fun ganarPremio(premio: Premio){
        premios.add(premio)
    }

    //fun modificarNombre(nuevoNombre: String) { nombre = nuevoNombre}
}