package ar.edu.unsam.algo3

import ar.edu.unsam.algo3.repos.ItemRepo

class Recomendacion(
    val creador: User,
    var titulo: String = "",
    var resegna: String,
    val libros: MutableSet<Libro> = mutableSetOf(),
    var publica: Boolean = false
) : ItemRepo {
    override var id: Int = -1

    val valoraciones: MutableList<Valoracion> = mutableListOf()

    init {
        creador.addRecom(this)
        if (!libros.all { puedeAgregarLibro(creador, it) })
            throw Exception("La lista de libros no es válida.")
    }

    fun creador() = creador

    fun puedeEditar(posibleEditor: User): Boolean =
        posibleEditor === creador || ((creador.isFriend(posibleEditor) &&
                posibleEditor.recomBooksAreRead(this))) // agrego parentensis porque me parece que tiene mas sentido asi

    private fun puedeAgregarLibro(editor: User, libro: Libro) = editor.bookIsRead(libro) &&
            creador.bookIsRead(libro)

    fun puedeValorar(valorador: User) =
        valorador !== creador &&
                (valorador.recomBooksAreRead(this) ||
                        (libros.all { it.autor() === libros.first().autor() } &&
                                valorador.isFavouriteAuthor(libros.first().autor())))

    fun cambiarPrivacidad(editor: User) {
        if (puedeEditar(editor)) {
            publica = !publica
        } else {
            throw Exception("No es un editor válido")
        }
    }

    fun esPublica(): Boolean = publica

    fun editarResegna(editor: User, nuevaResegna: String) {
        if (puedeEditar(editor)) {
            resegna = nuevaResegna
        } else {
            throw Exception("No es un editor válido")
        }
    }

    fun resegna(): String = resegna

    fun agregarLibro(editor: User, nuevoLibro: Libro) {
        if (puedeEditar(editor) && puedeAgregarLibro(editor, nuevoLibro)) {
            libros.add(nuevoLibro)
        } else {
            throw Exception("No es un editor válido")
        }
    }

    fun eliminarLibro(editor: User, libroAEliminar: Libro) {
        if (puedeEditar(editor)) {
            libros.remove(libroAEliminar)
        } else {
            throw Exception("No es un editor válido")
        }
    }

    fun libros(): MutableSet<Libro> = libros

    fun tiempoLecturaRecomendacion(user: User): Double =
        libros.sumOf { user.bookReadTime(it) }

    fun tiempoLecturaAhorrado(user: User): Double =
        libros.filter { user.bookIsRead(it) }.sumOf { user.bookReadTime(it) }

    fun tiempoLecturaNeto(user: User): Double =
        tiempoLecturaRecomendacion(user) - tiempoLecturaAhorrado(user)

    fun valoraciones() = valoraciones
    fun agregarValoracion(valoracion: Valoracion) {
        if (puedeValorar(valoracion.autor)) {
            valoraciones.add(valoracion)
        } else {
            throw Exception("No es un valorador válido")
        }
    }

    fun usuarioValoro(user: User) = valoraciones.any { it.autor === user }

    fun promedioValoraciones(): Double = valoraciones.map { it.rating }.average()

}