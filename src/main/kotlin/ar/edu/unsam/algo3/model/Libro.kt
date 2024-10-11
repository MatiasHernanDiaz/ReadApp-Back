package ar.edu.unsam.algo3

import ar.edu.unsam.algo3.repos.ItemRepo

//import java.time.temporal.ChronoUnit

class Libro(
    val titulo: String = "",
    val autor: Autor,
    val paginas: Int,
    val imageURL: String,
    val palabras: Int,
    var ediciones: Int,
    var ventasSemanales: Int,
    val lecturaCompleja: Boolean,
    val traducciones: MutableSet<Lenguaje> = mutableSetOf(),


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

    fun idiomaOriginal(): Lenguaje = autor.idiomaNativo()
    fun lenguajes() : List<Lenguaje> = traducciones.plus(idiomaOriginal()).toList()

    fun paginas(): Int = paginas // Necesita acceder Usuario

    fun titulo() = titulo
    
    fun autor(): Autor = autor

    fun editarEdiciones(cantidadEdiciones: Int) { ediciones = cantidadEdiciones }
    fun editarVentasSemanales(cantidadVentasSemanales: Int) { ventasSemanales = cantidadVentasSemanales }

    //fun getVentasSemanales(): Int = ventasSemanales
}

class Premio{

}