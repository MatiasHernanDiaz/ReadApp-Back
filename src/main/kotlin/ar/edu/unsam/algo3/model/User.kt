package ar.edu.unsam.algo3


import ar.edu.unsam.algo3.repos.ItemRepo
import java.time.LocalDate
import java.time.Period

class User(
    override var id: Int = -1,
    var firstName: String,
    var lastName: String,
    var username: String,
    var password: String?,
    var email: String,
    var birthday: LocalDate,
    var nativeLanguage: Language,
    var readTimeMinAvg: Int,
    var searchCriteria: SearchCriteria = GreatReader(),
    val friends: MutableSet<User> = mutableSetOf(),
    val readBooks: MutableList<Libro> = mutableListOf(),
    val booksToRead: MutableSet<Libro> = mutableSetOf(),
    val favouriteAuthors: MutableSet<Autor> = mutableSetOf(),
    val recommendations: MutableList<Recomendacion> = mutableListOf(),
    val ratings: MutableList<Valoracion> = mutableListOf(),
    var readMode: ReadMode = AvgReader,
    var avatar: String = ""
): ItemRepo {
    fun edad(): Int = Period.between(birthday, LocalDate.now()).years

    fun tiempoLecturaPromedio(libro: Libro): Double =
        if (libro.esDesafiante()) (libro.palabras().toDouble() / readTimeMinAvg) * 2 else (libro.palabras()
            .toDouble() / readTimeMinAvg)

    //fun crearRecomendacion(libros: MutableSet<Libro>, resegnia: String): Recomendacion =
    //    Recomendacion(this, resegnia, libros)

    fun agregarLibroLeido(libro: Libro) {
        readBooks.add(libro)
    }

    fun perfil(): SearchCriteria = searchCriteria

    fun listaLibrosLeidos(): MutableList<Libro> = readBooks

    fun libroLeido(libro: Libro): Boolean = readBooks.contains(libro)

    fun todosLosLibrosLeidos(recomendacion: Recomendacion) =
        recomendacion.libros().all({ readBooks.contains(it) })

    fun agregarLibroALeer(libro: Libro) {
        booksToRead.add(libro)
    }

    fun listaDeLibrosALeer(): MutableSet<Libro> = booksToRead

    fun valorarRecomendacion(recomendacion: Recomendacion, puntuacion: Int, comentario: String): Unit {
        val valoracion = Valoracion(puntuacion, comentario, this)
        recomendacion.agregarValoracion(valoracion)
        ratings.add(valoracion)
    }

    fun amigos() = friends

    fun agregarAmigo(user: User) {
        if (!esAmigo(user)) {
            friends.add(user)
        } else {
            throw Exception("${user.nombreCompleto()} ya pertenece a la lista de amigos.")
        }
    }

    fun eliminarAmigo(user: User) {
        if (esAmigo(user)) {
            friends.remove(user)
        }
        else {
            throw Exception("${user.nombreCompleto()} no pertenece a la lista de amigos.")
        }
    }

    fun modificarPerfil(nuevoSearchCriteria: SearchCriteria) {
        searchCriteria = nuevoSearchCriteria
    }

    fun cantidadDeReleida(libro: Libro): Int = readBooks.count({ it == libro })

    fun esAmigo(amigo: User): Boolean = friends.contains(amigo)

    fun esAutorPreferido(autor: Autor): Boolean = favouriteAuthors.contains(autor)

    fun cambiarTipoLector(tipo: ReadMode) { readMode = tipo }

    fun esRecomendable(recomendacion: Recomendacion): Boolean {
        return perfil().esRecomendable(recomendacion)
    }

    fun tiempoLecturaLibro(libro: Libro) = readMode.readTime(libro, this)

    fun tipoDeLector() = readMode

    fun agregarAutorPreferido(autor: Autor) = favouriteAuthors.add(autor)

    fun fechaNacimiento() = birthday
    fun lenguajeNativo() = nativeLanguage

    fun nombreCompleto() = firstName + " " + lastName

    fun apellido() = lastName

    fun userName() = username

    fun agregarRecomendacion(reco: Recomendacion){ recommendations.add(reco)}

    fun tieneRecomendaciones(): Boolean = !recommendations.isEmpty()

    fun tieneValoraciones(): Boolean = !ratings.isEmpty()

    fun getMail()=email

}



