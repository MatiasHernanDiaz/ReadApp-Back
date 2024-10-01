package ar.edu.unsam.algo3

interface Perfil{
    fun esRecomendable(recomendacion: Recomendacion): Boolean
}

class Precavido(val usuario: Usuario): Perfil {
    override fun esRecomendable(recomendacion: Recomendacion): Boolean {
        return (recomendacion.libros().any{ libro -> usuario.listaDeLibrosALeer().contains(libro)}) || (recomendacion.libros().any{ libro -> usuario.amigos().any{ amigo -> amigo.libroLeido(libro)}})
    }
}
class Leedor: Perfil{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean = true
}
class Poliglota: Perfil{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.libros().map{ libro -> libro.lenguajes()}.flatten().toSet().size >= 5
    }
}
class Nativista(val usuario: Usuario): Perfil{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.libros().any{ libro -> usuario.lenguajeNativo() == libro.idiomaOriginal()}
    }
}
class Calculador(val usuario: Usuario, val tiempoMinimo: Double, val tiempoMaximo: Double): Perfil{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return (recomendacion.tiempoLecturaNeto(usuario) >= tiempoMinimo) && (recomendacion.tiempoLecturaNeto(usuario) <= tiempoMaximo)
    }
}
class Demandante: Perfil{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.promedioValoraciones() >= 4.0
    }
}
class Experimentado: Perfil{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return recomendacion.libros().any{ libro -> libro.autor().esConsagrado()}
    }
}
class Cambiante(val usuario: Usuario, val calculador: Calculador): Perfil{
    val leedor = Leedor()
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        if(usuario.edad() < 25){
            return leedor.esRecomendable(recomendacion)
        }
        else return calculador.esRecomendable(recomendacion)
    }
}

class Combinado(val usuario: Usuario, val perfiles: MutableSet<Perfil>):Perfil{
    override fun esRecomendable(recomendacion: Recomendacion): Boolean{
        return perfiles.all{perfil ->  perfil.esRecomendable(recomendacion)}
    }
}
