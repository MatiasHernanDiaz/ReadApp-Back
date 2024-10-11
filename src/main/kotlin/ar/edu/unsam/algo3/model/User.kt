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
    val favouriteAuthors: MutableSet<Author> = mutableSetOf(),
    val recommendations: MutableList<Recomendacion> = mutableListOf(),
    val ratings: MutableList<Valoracion> = mutableListOf(),
    var readMode: ReadMode = AvgReader,
    var avatar: String = ""
): ItemRepo {
    fun age(): Int = Period.between(birthday, LocalDate.now()).years

    fun readTimeAvg(libro: Libro): Double =
        if (libro.esDesafiante()) (libro.palabras().toDouble() / readTimeMinAvg) * 2 else (libro.palabras()
            .toDouble() / readTimeMinAvg)

    //fun crearRecomendacion(libros: MutableSet<Libro>, resegnia: String): Recomendacion =
    //    Recomendacion(this, resegnia, libros)

    fun addReadBook(libro: Libro) {
        readBooks.add(libro)
    }

    fun searchCriteria(): SearchCriteria = searchCriteria

    fun readBooks(): MutableList<Libro> = readBooks

    fun bookIsRead(libro: Libro): Boolean = readBooks.contains(libro)

    fun recomBooksAreRead(recomendacion: Recomendacion) =
        recomendacion.libros().all({ readBooks.contains(it) })

    fun addBookToRead(libro: Libro) {
        booksToRead.add(libro)
    }

    fun booksToRead(): MutableSet<Libro> = booksToRead

    fun rateRecom(recomendacion: Recomendacion, puntuacion: Int, comentario: String): Unit {
        val valoracion = Valoracion(puntuacion, comentario, this)
        recomendacion.agregarValoracion(valoracion)
        ratings.add(valoracion)
    }

    fun friends() = friends

    fun addFriend(user: User) {
        if (!isFriend(user)) {
            friends.add(user)
        } else {
            throw Exception("${user.displayName()} ya pertenece a la lista de amigos.")
        }
    }

    fun deleteFriend(user: User) {
        if (isFriend(user)) {
            friends.remove(user)
        }
        else {
            throw Exception("${user.displayName()} no pertenece a la lista de amigos.")
        }
    }

    fun updateSearchCriteria(nuevoSearchCriteria: SearchCriteria) {
        searchCriteria = nuevoSearchCriteria
    }

    fun reReadAmount(libro: Libro): Int = readBooks.count({ it == libro })

    fun isFriend(amigo: User): Boolean = friends.contains(amigo)

    fun isFavouriteAuthor(autor: Author): Boolean = favouriteAuthors.contains(autor)

    fun updateReadMode(tipo: ReadMode) { readMode = tipo }

    fun isRecommendable(recomendacion: Recomendacion): Boolean {
        return searchCriteria().esRecomendable(recomendacion)
    }

    fun bookReadTime(libro: Libro) = readMode.readTime(libro, this)

    fun readMode() = readMode

    fun addFavouriteAuthor(autor: Author) = favouriteAuthors.add(autor)

    fun birthday() = birthday
    fun nativeLanguage() = nativeLanguage

    fun displayName() = firstName + " " + lastName

    fun lastName() = lastName

    fun username() = username

    fun addRecom(reco: Recomendacion){ recommendations.add(reco)}

    fun hasRecoms(): Boolean = !recommendations.isEmpty()

    fun hasRatings(): Boolean = !ratings.isEmpty()

    fun getMail()=email

    fun removeRecomendation(recomendacion: Recomendacion) = recommendations.remove(recomendacion)

}



