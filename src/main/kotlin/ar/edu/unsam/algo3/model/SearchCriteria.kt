package ar.edu.unsam.algo3

interface SearchCriteria{
    fun esRecomendable(recomendacion: Recomendacion): Boolean
    fun toCustomString(): String

    companion object {
        fun fromCustomString(
            s: String, user: User? = null, minTime: Double? = null, maxTime: Double? = null, profiles: List<String>? = null
        ): SearchCriteria = when(s) {
            "Precavido" -> Cautious(user!!)
            "Leedor" -> GreatReader()
            "Políglota" -> Polyglot()
            "Nativista" -> Nativist(user!!)
            "Calculador" -> Calculator(user!!, minTime!!, maxTime!!)
            "Demandante" -> Claimant()
            "Experimentado" -> Experiencied()
            "Cambiante" -> Inconstant(user!!, Calculator(user!!, minTime!!, maxTime!!))
            "Combinado" -> Combined(user!!, profiles!!.map{ this.fromCustomString(it) }.toMutableSet())
            else -> GreatReader()
         }
    }
}

class Cautious(val user: User): SearchCriteria {
    override fun esRecomendable(recomendacion: Recomendacion): Boolean {
        return (recomendacion.libros().any{ libro -> user.booksToRead().contains(libro)}) || (recomendacion.libros().any{ libro -> user.friends().any{ amigo -> amigo.bookIsRead(libro)}})
    }

    override fun toCustomString(): String = "Precavido"
}
class GreatReader: SearchCriteria {
    override fun esRecomendable(recomendacion: Recomendacion): Boolean = true
    override fun toCustomString(): String = "Leedor"
}
class Polyglot: SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.libros().map{ libro -> libro.lenguajes()}.flatten().toSet().size >= 5
    }

    override fun toCustomString(): String = "Políglota"
}
class Nativist(val user: User): SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.libros().any{ libro -> user.nativeLanguage() == libro.idiomaOriginal()}
    }

    override fun toCustomString(): String = "Nativista"
}
class Calculator(val user: User, val tiempoMinimo: Double, val tiempoMaximo: Double): SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return (recomendacion.tiempoLecturaNeto(user) >= tiempoMinimo) && (recomendacion.tiempoLecturaNeto(user) <= tiempoMaximo)
    }

    override fun toCustomString(): String = "Calculador"
}
class Claimant: SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.promedioValoraciones() >= 4.0
    }

    override fun toCustomString(): String = "Demandante"
}
class Experiencied: SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.libros().any{ libro -> libro.autor().isDedicated()}
    }

    override fun toCustomString(): String = "Experimentado"
}
class Inconstant(val user: User, val calculator: Calculator): SearchCriteria{
    val greatReader = GreatReader()
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        if(user.age() < 25){
            return greatReader.esRecomendable(recomendacion)
        }
        else return calculator.esRecomendable(recomendacion)
    }

    override fun toCustomString(): String = "Cambiante"
}

class Combined(val user: User, val perfiles: MutableSet<SearchCriteria>):SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return perfiles.all{perfil ->  perfil.esRecomendable(recomendacion)}
    }

    override fun toCustomString(): String = "Combinado"
}
