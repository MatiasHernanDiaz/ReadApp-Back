package ar.edu.unsam.algo3

interface SearchCriteria{
    val minTime: Double
    val maxTime: Double
    fun esRecomendable(recomendacion: Recomendacion): Boolean
    fun toCustomString(): String

    companion object {
        fun fromCustomString(
            s: String, user: User? = null, minTime: Double, maxTime: Double, profiles: List<String>? = null
        ): SearchCriteria = when(s) {
            "Precavido" -> Cautious(user!!)
            "Leedor" -> GreatReader()
            "Políglota" -> Polyglot()
            "Nativista" -> Nativist(user!!)
            "Calculador" -> Calculator(user!!, minTime, maxTime)
            "Demandante" -> Claimant()
            "Experimentado" -> Experiencied()
            "Cambiante" -> Inconstant(user!!, minTime, maxTime)
            "Combinado" -> Combined(user!!, minTime, maxTime, profiles!!.map{ this.fromCustomString(it, user, minTime, maxTime) }.toMutableSet())
            else -> GreatReader()
         }
    }
}

class Cautious(val user: User, override val minTime: Double = 0.0, override val maxTime: Double = 0.0): SearchCriteria {
    override fun esRecomendable(recomendacion: Recomendacion): Boolean {
        return (recomendacion.libros().any{ libro -> user.booksToRead().contains(libro)}) || (recomendacion.libros().any{ libro -> user.friends().any{ amigo -> amigo.bookIsRead(libro)}})
    }

    override fun toCustomString(): String = "Precavido"
}
class GreatReader(override val minTime: Double = 0.0, override val maxTime: Double = 0.0): SearchCriteria {
    override fun esRecomendable(recomendacion: Recomendacion): Boolean = true
    override fun toCustomString(): String = "Leedor"
}
class Polyglot(override val minTime: Double = 0.0, override val maxTime: Double = 0.0): SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.libros().map{ libro -> libro.lenguajes()}.flatten().toSet().size >= 5
    }

    override fun toCustomString(): String = "Políglota"
}
class Nativist(val user: User, override val minTime: Double = 0.0, override val maxTime: Double = 0.0): SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.libros().any{ libro -> user.nativeLanguage() == libro.idiomaOriginal()}
    }

    override fun toCustomString(): String = "Nativista"
}
class Calculator(val user: User, override val minTime: Double, override val maxTime: Double): SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return (recomendacion.tiempoLecturaNeto(user) >= minTime) && (recomendacion.tiempoLecturaNeto(user) <= maxTime)
    }

    override fun toCustomString(): String = "Calculador"
}
class Claimant(override val minTime: Double = 0.0, override val maxTime: Double = 0.0): SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.promedioValoraciones() >= 4.0
    }

    override fun toCustomString(): String = "Demandante"
}
class Experiencied(override val minTime: Double = 0.0, override val maxTime: Double = 0.0): SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.libros().any{ libro -> libro.autor().isDedicated()}
    }

    override fun toCustomString(): String = "Experimentado"
}
class Inconstant(val user: User, override val minTime: Double, override val maxTime: Double): SearchCriteria{
    val greatReader = GreatReader()
    val calculator = Calculator(user, minTime, maxTime)
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        if(user.age() < 25){
            return greatReader.esRecomendable(recomendacion)
        }
        else return calculator.esRecomendable(recomendacion)
    }

    override fun toCustomString(): String = "Cambiante"
}

class Combined(val user: User, override val minTime: Double = 0.0, override val maxTime: Double = 0.0, val perfiles: MutableSet<SearchCriteria>):SearchCriteria{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return perfiles.all{perfil ->  perfil.esRecomendable(recomendacion)}
    }

    override fun toCustomString(): String = "Combinado"
}
