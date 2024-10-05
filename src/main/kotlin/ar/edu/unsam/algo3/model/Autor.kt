package ar.edu.unsam.algo3
import ar.edu.unsam.algo3.repos.ItemRepo
import java.time.LocalDate
import java.time.Period


class Autor (
    private val apellido: String,
    private var nombre: String,
    private val seudonimo: String,
    private val fechaNacimiento : LocalDate,
    val idiomaNativo: Lenguaje,
    private val premios : MutableList<Premio> = mutableListOf()
) : ItemRepo {
    override var id: UInt? = null

    fun apellido() = apellido
    fun nombre() = nombre
    fun seudonimo() = seudonimo
    fun idiomaNativo(): Lenguaje = idiomaNativo
    fun edad(): Int = Period.between(fechaNacimiento, LocalDate.now()).years
    fun esConsagrado(): Boolean = premios.isNotEmpty() or (edad() >= 50)

    fun ganarPremio(premio: Premio){
        premios.add(premio)
    }

    //fun modificarNombre(nuevoNombre: String) { nombre = nuevoNombre}
}