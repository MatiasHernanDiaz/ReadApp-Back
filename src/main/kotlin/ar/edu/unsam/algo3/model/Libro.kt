package ar.edu.unsam.algo3

import ar.edu.unsam.algo3.repos.ItemRepo
import ar.edu.unsam.algo3.Author

//import java.time.temporal.ChronoUnit

class Libro(
    val titulo: String = "",
    val autor: Author,
    val imagenURL: String,
    val paginas: Int,
    val palabras: Int,
    var ediciones: Int,
    var fecha: Int,
    var ventasSemanales: Int,
    val lecturaCompleja: Boolean,
    val traducciones: MutableSet<Language> = mutableSetOf(),

    ) : ItemRepo {
    companion object maxPag {
        val maxPaginasLibroLargo = 600

        fun maxPaginasLibroLargo() = maxPaginasLibroLargo
    }
    override var id: Int = -1


    fun palabras(): Int = palabras
    fun esDesafiante() = lecturaCompleja or (paginas > maxPaginasLibroLargo)
    fun esBestSeller(): Boolean = (ventasSemanales > 10000) and ((ediciones > 2) or (lenguajes().size > 5))

    fun traducciones() = traducciones

    fun idiomaOriginal(): Language = autor.nativeLanguage()
    fun lenguajes() : List<Language> = traducciones.plus(idiomaOriginal()).toList()

    fun paginas(): Int = paginas // Necesita acceder Usuario

    fun titulo() = titulo
    
    fun autor(): Author = autor

    fun editarEdiciones(cantidadEdiciones: Int) { ediciones = cantidadEdiciones }
    fun editarVentasSemanales(cantidadVentasSemanales: Int) { ventasSemanales = cantidadVentasSemanales }

    //fun getVentasSemanales(): Int = ventasSemanales
}

class Premio{

}