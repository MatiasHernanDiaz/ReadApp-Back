package ar.edu.unsam.algo3

interface ReadMode {
    fun readTime(libro: Libro, user: User): Double

    fun toCustomString(): String

    companion object {
        fun fromCustomString(s: String): ReadMode = when(s) {
            "Promedio" -> AvgReader
            "Ansioso" -> anxiousReader
            "Fanático" -> fanaticReader
            "Recurrente" -> recurrentReader
            else -> AvgReader
        }
    }
}

object AvgReader : ReadMode {
    override fun readTime(libro: Libro, user: User): Double = user.tiempoLecturaPromedio(libro)
    override fun toCustomString(): String = "Promedio"
}

object anxiousReader : ReadMode {
    override fun readTime(libro: Libro, user: User): Double =
        if (libro.esBestSeller()) {
            user.tiempoLecturaPromedio(libro) * 0.5
        } else {
            user.tiempoLecturaPromedio(libro) * 0.8
        }

    override fun toCustomString(): String = "Ansioso"
}


object fanaticReader : ReadMode {
    override fun readTime(libro: Libro, user: User): Double {
        //llaves maurio joroba
        val tiempoExtra =
            if (user.esAutorPreferido(libro.autor()) && !user.libroLeido(libro))
                if (libro.paginas() > Libro.maxPaginasLibroLargo())
                    Libro.maxPaginasLibroLargo() * 2 + (libro.paginas() - Libro.maxPaginasLibroLargo())
                else libro.paginas() * 2
            else 0

        return user.tiempoLecturaPromedio(libro) + tiempoExtra
    }

    override fun toCustomString(): String = "Fanático"

}

object recurrentReader : ReadMode {
    override fun readTime(libro: Libro, user: User): Double =
        user.tiempoLecturaPromedio(libro) - user.tiempoLecturaPromedio(libro) *
                listOf(0.01 * user.cantidadDeReleida(libro), 0.05).min()

    override fun toCustomString(): String = "Recurrente"
}