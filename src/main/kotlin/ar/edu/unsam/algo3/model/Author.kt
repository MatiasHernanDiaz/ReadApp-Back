package ar.edu.unsam.algo3
import ar.edu.unsam.algo3.repos.ItemRepo
import java.time.LocalDate
import java.time.Period


class Author (
    val lastName: String,
    var firstName: String,
    val alias: String,
    val birthday : LocalDate,
    val nativeLanguage: Language,
    val prices : MutableList<Premio> = mutableListOf()
) : ItemRepo {
    override var id: Int = -1

    fun lastName() = lastName
    fun firstName() = firstName
    fun alias() = alias
    fun nativeLanguage(): Language = nativeLanguage
    fun age(): Int = Period.between(birthday, LocalDate.now()).years
    fun isDedicated(): Boolean = prices.isNotEmpty() or (age() >= 50)

    fun winPrice(premio: Premio){
        prices.add(premio)
    }

    //fun modificarNombre(nuevoNombre: String) { nombre = nuevoNombre}
}