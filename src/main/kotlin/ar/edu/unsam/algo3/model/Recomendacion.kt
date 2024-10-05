package ar.edu.unsam.algo3

import ar.edu.unsam.algo3.repos.ItemRepo

class Recomendacion(
    val creador: Usuario,
    var resegna: String,
    val libros: MutableSet<Libro> = mutableSetOf(),
    var publica: Boolean = false
) : ItemRepo {
    override var id: Int = -1

    val valoraciones: MutableList<Valoracion> = mutableListOf()
    val observers: MutableSet<AddLibrosObserver> = mutableSetOf()

    init {
        creador.agregarRecomendacion(this)
        if(!libros.all { puedeAgregarLibro(creador, it) })
            throw Exception("La lista de libros no es válida.")
    }

    fun creador() = creador

    private fun puedeEditar(posibleEditor: Usuario): Boolean =
        posibleEditor === creador || (creador.esAmigo(posibleEditor) &&
                posibleEditor.todosLosLibrosLeidos(this))

    private fun puedeAgregarLibro(editor: Usuario, libro: Libro) = editor.libroLeido(libro) &&
            creador.libroLeido(libro)

    private fun puedeValorar(valorador: Usuario) =
        valorador !== creador &&
                (valorador.todosLosLibrosLeidos(this) ||
                        (libros.all { it.autor() === libros.first().autor() } &&
                                valorador.esAutorPreferido(libros.first().autor())))

    fun cambiarPrivacidad(editor: Usuario) {
        if(puedeEditar(editor)) {
            publica = !publica
        } else {
            throw Exception("No es un editor válido")
        }
    }

    fun esPublica(): Boolean = publica

    fun editarResegna(editor: Usuario, nuevaResegna: String) {
        if(puedeEditar(editor)) {
            resegna = nuevaResegna
        } else {
            throw Exception("No es un editor válido")
        }
    }

    fun resegna(): String = resegna

    fun agregarLibro(editor: Usuario, nuevoLibro: Libro) {
        if(puedeEditar(editor) && puedeAgregarLibro(editor, nuevoLibro)) {
            libros.add(nuevoLibro)
        }
        else {
            throw Exception("No es un editor válido")
        }
        observers.forEach { it.libroAgregado(editor,nuevoLibro) }
    }

    fun eliminarLibro(editor: Usuario, libroAEliminar: Libro) {
        if(puedeEditar(editor)) {
            libros.remove(libroAEliminar)
        }
        else {
            throw Exception("No es un editor válido")
        }
    }

    fun libros(): MutableSet<Libro> = libros

    fun tiempoLecturaRecomendacion(usuario: Usuario): Double =
        libros.sumOf { usuario.tiempoLecturaLibro(it) }

    fun tiempoLecturaAhorrado(usuario: Usuario): Double =
        libros.filter { usuario.libroLeido(it) } .sumOf { usuario.tiempoLecturaLibro(it) }

    fun tiempoLecturaNeto(usuario: Usuario): Double =
        tiempoLecturaRecomendacion(usuario) - tiempoLecturaAhorrado(usuario)

    fun valoraciones() = valoraciones
    fun agregarValoracion(valoracion: Valoracion) {
        if(puedeValorar(valoracion.autor)) {
            valoraciones.add(valoracion)
        } else {
            throw Exception("No es un valorador válido")
        }
    }

    fun usuarioValoro(usuario: Usuario) = valoraciones.any { it.autor === usuario }

    fun promedioValoraciones(): Double = valoraciones.map{ it.puntuacion }.average()

    fun agregarAddLibrosObserver(observer: AddLibrosObserver){ observers.add(observer) }
}