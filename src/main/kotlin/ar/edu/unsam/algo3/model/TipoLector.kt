package ar.edu.unsam.algo3

interface TipoLector {
    fun tiempoLectura(libro: Libro, usuario: Usuario): Double
}

object LectorPromedio : TipoLector {
    override fun tiempoLectura(libro: Libro, usuario: Usuario): Double = usuario.tiempoLecturaPromedio(libro)
}

object LectorAnsioso : TipoLector {
    override fun tiempoLectura(libro: Libro, usuario: Usuario): Double =
        if (libro.esBestSeller()) {
            usuario.tiempoLecturaPromedio(libro) * 0.5
        } else {
            usuario.tiempoLecturaPromedio(libro) * 0.8
        }
}


object LectorFanatico : TipoLector {
    override fun tiempoLectura(libro: Libro, usuario: Usuario): Double {
        //llaves maurio joroba
        val tiempoExtra =
            if (usuario.esAutorPreferido(libro.autor()) && !usuario.libroLeido(libro))
                if (libro.paginas() > Libro.maxPaginasLibroLargo())
                    Libro.maxPaginasLibroLargo() * 2 + (libro.paginas() - Libro.maxPaginasLibroLargo())
                else libro.paginas() * 2
            else 0

        return usuario.tiempoLecturaPromedio(libro) + tiempoExtra
    }

}

object LectorRecurrente : TipoLector {
    override fun tiempoLectura(libro: Libro, usuario: Usuario): Double =
        usuario.tiempoLecturaPromedio(libro) - usuario.tiempoLecturaPromedio(libro) *
                listOf(0.01 * usuario.cantidadDeReleida(libro), 0.05).min()
}