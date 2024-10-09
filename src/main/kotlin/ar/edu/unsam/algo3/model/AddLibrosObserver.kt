package ar.edu.unsam.algo3

interface AddLibrosObserver {
    fun libroAgregado(editor: User, nuevoLibro: Libro): Unit

}

class Aportes(val user: User, val librosAgregados: MutableSet<Libro>){
    fun nuevoAporte(libro:Libro){
        librosAgregados.add(libro)
    }
    fun cantAportados(): Int = librosAgregados.size
    fun librosAportados(): Set<Libro> = librosAgregados
}

class NotificacionUsuario(private val enviadorMail: EnviaMail,val recomendacion: Recomendacion) : AddLibrosObserver {
    override fun libroAgregado(user: User, libro: Libro) {
        if (user != recomendacion.creador()) {
            val mail = Mail(
                from = "Notificaciones@readapp.com.ar",
                to = recomendacion.creador().getMail(),
                subject = "Se agregó un Libro",
                content = "El usuario: ${user.nombreCompleto()} agregó el Libro ${libro.titulo()} " +
                        "a la recomendación que tenía estos " +
                        "Títulos: ${recomendacion.libros().joinToString(", ") { it.titulo() }}"
            )
            enviadorMail.envioMail(mail)
        }
    }
}


class RegistroAportes : AddLibrosObserver{
    val aportes: MutableList<Aportes> = mutableListOf()
    override fun libroAgregado(editor: User, nuevoLibro: Libro) {
        //THEO
        if(!aportes.map{aporte -> aporte.user}.contains(editor)){
            aportes.add(Aportes(user = editor, librosAgregados = mutableSetOf()))
        }
        aporteUsuario(editor).nuevoAporte(nuevoLibro)
    }

    fun aporteUsuario(user: User): Aportes{
        return aportes.filter{ aporte -> aporte.user == user}.first()
    }
    fun cantAportes(user: User): Int = aporteUsuario(user).cantAportados()
    fun librosAportadosPor(user: User): Set<Libro> = aporteUsuario(user).librosAportados()
}

class LimitadorLibrosAgregados(val recomendacion: Recomendacion,var limiteLibros: Int) : AddLibrosObserver{

    private val mapaUserLibros: MutableMap<User, Int> = mutableMapOf()

    override fun libroAgregado(editor: User, nuevoLibro: Libro) {

        if(mapaUserLibros.containsKey(editor)){
            mapaUserLibros[editor] = mapaUserLibros[editor]!! + 1
        }
        else{
            mapaUserLibros[editor] = 1
        }

        if(limiteLibros <= mapaUserLibros[editor]!!){
            recomendacion.creador().eliminarAmigo(editor)
        }
    }

    fun cambiarLimite(limite: Int): Unit { limiteLibros = limite }

    fun getCantidadLibros(user: User) = mapaUserLibros[user]

}

class ValoracionAutomatica(val recomendacion: Recomendacion) : AddLibrosObserver {
    override fun libroAgregado(editor: User, nuevoLibro: Libro) {
        if( editor !== recomendacion.creador() && !recomendacion.usuarioValoro(editor) ) {
            recomendacion.agregarValoracion(Valoracion(5, "Excelente 100% recomendable", editor))
        }
    }
}