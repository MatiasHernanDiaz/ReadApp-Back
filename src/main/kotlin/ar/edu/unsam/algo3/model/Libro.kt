package ar.edu.unsam.algo3

//import java.time.temporal.ChronoUnit

class Libro(
    private val titulo: String = "",
    private val autor: Autor,
    private val paginas: Int,
    private val palabras: Int,
    private var ediciones: Int,
    private var ventasSemanales: Int,
    private val lecturaCompleja: Boolean,
    private val traducciones: MutableSet<Lenguaje> = mutableSetOf(),

    ) : ItemRepo {
    companion object maxPag {
        private val maxPaginasLibroLargo = 600

        fun maxPaginasLibroLargo() = maxPaginasLibroLargo
    }
    override var id: UInt? = null


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

    fun getVentasSemanales()=ventasSemanales
}

class Premio{

}