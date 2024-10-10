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
    override fun readTime(libro: Libro, user: User): Double = user.readTimeAvg(libro)
    override fun toCustomString(): String = "Promedio"
}

object anxiousReader : ReadMode {
    override fun readTime(libro: Libro, user: User): Double =
        if (libro.esBestSeller()) {
            user.readTimeAvg(libro) * 0.5
        } else {
            user.readTimeAvg(libro) * 0.8
        }

    override fun toCustomString(): String = "Ansioso"
}


object fanaticReader : ReadMode {
    override fun readTime(libro: Libro, user: User): Double {
        //llaves maurio joroba
        val tiempoExtra =
            if (user.isFavouriteAuthor(libro.autor()) && !user.bookIsRead(libro))
                if (libro.paginas() > Libro.maxPaginasLibroLargo())
                    Libro.maxPaginasLibroLargo() * 2 + (libro.paginas() - Libro.maxPaginasLibroLargo())
                else libro.paginas() * 2
            else 0

        return user.readTimeAvg(libro) + tiempoExtra
    }

    override fun toCustomString(): String = "Fanático"

}

object recurrentReader : ReadMode {
    override fun readTime(libro: Libro, user: User): Double =
        user.readTimeAvg(libro) - user.readTimeAvg(libro) *
                listOf(0.01 * user.reReadAmount(libro), 0.05).min()

    override fun toCustomString(): String = "Recurrente"
}