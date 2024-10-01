package ar.edu.unsam.algo3


import java.time.LocalDate
import java.time.Period

class Usuario(
    private val nombre: String,
    private val apellido: String,
    private val userName: String,
    private val email: String,
    private val fechaNacimiento: LocalDate,
    private var perfil: Perfil,
    private val lenguajeNativo: Lenguaje,
    private val palabrasXMinuto: Int,
    private val amigos: MutableSet<Usuario> = mutableSetOf(),
    private val librosLeidos: MutableList<Libro> = mutableListOf(),
    private val librosALeer: MutableSet<Libro> = mutableSetOf(),
    private val autoresPreferidos: MutableSet<Autor> = mutableSetOf(),
    private val recomendaciones: MutableList<Recomendacion> = mutableListOf(),
    private val valoraciones: MutableList<Valoracion> = mutableListOf(),
    private var tipoLector: TipoLector = LectorPromedio
): ItemRepo {
    override var id: UInt? = null

    fun edad(): Int = Period.between(fechaNacimiento, LocalDate.now()).years

    fun tiempoLecturaPromedio(libro: Libro): Double =
        if (libro.esDesafiante()) (libro.palabras().toDouble() / palabrasXMinuto) * 2 else (libro.palabras()
            .toDouble() / palabrasXMinuto)

    //fun crearRecomendacion(libros: MutableSet<Libro>, resegnia: String): Recomendacion =
    //    Recomendacion(this, resegnia, libros)

    fun agregarLibroLeido(libro: Libro) {
        librosLeidos.add(libro)
    }

    fun perfil(): Perfil = perfil

    fun listaLibrosLeidos(): MutableList<Libro> = librosLeidos

    fun libroLeido(libro: Libro): Boolean = librosLeidos.contains(libro)

    fun todosLosLibrosLeidos(recomendacion: Recomendacion) =
        recomendacion.libros().all({ librosLeidos.contains(it) })

    fun agregarLibroALeer(libro: Libro) {
        librosALeer.add(libro)
    }

    fun listaDeLibrosALeer(): MutableSet<Libro> = librosALeer

    fun valorarRecomendacion(recomendacion: Recomendacion, puntuacion: Int, comentario: String): Unit {
        val valoracion = Valoracion(puntuacion, comentario, this)
        recomendacion.agregarValoracion(valoracion)
        valoraciones.add(valoracion)
    }

    fun amigos() = amigos

    fun agregarAmigo(usuario: Usuario) {
        if (!esAmigo(usuario)) {
            amigos.add(usuario)
        } else {
            throw Exception("${usuario.nombreCompleto()} ya pertenece a la lista de amigos.")
        }
    }

    fun eliminarAmigo(usuario: Usuario) {
        if (esAmigo(usuario)) {
            amigos.remove(usuario)
        }
        else {
            throw Exception("${usuario.nombreCompleto()} no pertenece a la lista de amigos.")
        }
    }

    fun modificarPerfil(nuevoPerfil: Perfil) {
        perfil = nuevoPerfil
    }

    fun cantidadDeReleida(libro: Libro): Int = librosLeidos.count({ it == libro })

    fun esAmigo(amigo: Usuario): Boolean = amigos.contains(amigo)

    fun esAutorPreferido(autor: Autor): Boolean = autoresPreferidos.contains(autor)

    fun cambiarTipoLector(tipo: TipoLector) { tipoLector = tipo }

    fun esRecomendable(recomendacion: Recomendacion): Boolean {
        return perfil().esRecomendable(recomendacion)
    }

    fun tiempoLecturaLibro(libro: Libro) = tipoLector.tiempoLectura(libro, this)

    fun tipoDeLector() = tipoLector

    fun agregarAutorPreferido(autor: Autor) = autoresPreferidos.add(autor)

    fun fechaNacimiento() = fechaNacimiento
    fun lenguajeNativo() = lenguajeNativo

    fun nombreCompleto() = nombre + " " + apellido

    fun apellido() = apellido

    fun userName() = userName

    fun agregarRecomendacion(reco: Recomendacion){ recomendaciones.add(reco)}

    fun tieneRecomendaciones(): Boolean = !recomendaciones.isEmpty()

    fun tieneValoraciones(): Boolean = !valoraciones.isEmpty()

    fun getMail()=email

}



