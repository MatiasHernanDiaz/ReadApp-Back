package ar.edu.unsam.algo3

interface TipoLector {
    fun tiempoLectura(libro: Libro, user: User): Double
}

object LectorPromedio : TipoLector {
    override fun tiempoLectura(libro: Libro, user: User): Double = user.tiempoLecturaPromedio(libro)
}

object LectorAnsioso : TipoLector {
    override fun tiempoLectura(libro: Libro, user: User): Double =
        if (libro.esBestSeller()) {
            user.tiempoLecturaPromedio(libro) * 0.5
        } else {
            user.tiempoLecturaPromedio(libro) * 0.8
        }
}


object LectorFanatico : TipoLector {
    override fun tiempoLectura(libro: Libro, user: User): Double {
        //llaves maurio joroba
        val tiempoExtra =
            if (user.esAutorPreferido(libro.autor()) && !user.libroLeido(libro))
                if (libro.paginas() > Libro.maxPaginasLibroLargo())
                    Libro.maxPaginasLibroLargo() * 2 + (libro.paginas() - Libro.maxPaginasLibroLargo())
                else libro.paginas() * 2
            else 0

        return user.tiempoLecturaPromedio(libro) + tiempoExtra
    }

}

object LectorRecurrente : TipoLector {
    override fun tiempoLectura(libro: Libro, user: User): Double =
        user.tiempoLecturaPromedio(libro) - user.tiempoLecturaPromedio(libro) *
                listOf(0.01 * user.cantidadDeReleida(libro), 0.05).min()
}