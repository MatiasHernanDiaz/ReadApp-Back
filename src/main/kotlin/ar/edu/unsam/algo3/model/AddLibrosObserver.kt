package ar.edu.unsam.algo3

interface AddLibrosObserver {
    fun libroAgregado(editor: Usuario, nuevoLibro: Libro): Unit

}

class Aportes(val usuario: Usuario,val librosAgregados: MutableSet<Libro>){
    fun nuevoAporte(libro:Libro){
        librosAgregados.add(libro)
    }
    fun cantAportados(): Int = librosAgregados.size
    fun librosAportados(): Set<Libro> = librosAgregados
}

class NotificacionUsuario(private val enviadorMail: EnviaMail,val recomendacion: Recomendacion) : AddLibrosObserver {
    override fun libroAgregado(usuario: Usuario, libro: Libro) {
        if (usuario != recomendacion.creador()) {
            val mail = Mail(
                from = "Notificaciones@readapp.com.ar",
                to = recomendacion.creador().getMail(),
                subject = "Se agregó un Libro",
                content = "El usuario: ${usuario.nombreCompleto()} agregó el Libro ${libro.titulo()} " +
                        "a la recomendación que tenía estos " +
                        "Títulos: ${recomendacion.libros().joinToString(", ") { it.titulo() }}"
            )
            enviadorMail.envioMail(mail)
        }
    }
}


class RegistroAportes : AddLibrosObserver{
    val aportes: MutableList<Aportes> = mutableListOf()
    override fun libroAgregado(editor: Usuario, nuevoLibro: Libro) {
        //THEO
        if(!aportes.map{aporte -> aporte.usuario}.contains(editor)){
            aportes.add(Aportes(usuario = editor, librosAgregados = mutableSetOf()))
        }
        aporteUsuario(editor).nuevoAporte(nuevoLibro)
    }

    fun aporteUsuario(usuario: Usuario): Aportes{
        return aportes.filter{ aporte -> aporte.usuario == usuario}.first()
    }
    fun cantAportes(usuario: Usuario): Int = aporteUsuario(usuario).cantAportados()
    fun librosAportadosPor(usuario: Usuario): Set<Libro> = aporteUsuario(usuario).librosAportados()
}

class LimitadorLibrosAgregados(val recomendacion: Recomendacion,var limiteLibros: Int) : AddLibrosObserver{

    private val mapaUsuarioLibros: MutableMap<Usuario, Int> = mutableMapOf()

    override fun libroAgregado(editor: Usuario, nuevoLibro: Libro) {

        if(mapaUsuarioLibros.containsKey(editor)){
            mapaUsuarioLibros[editor] = mapaUsuarioLibros[editor]!! + 1
        }
        else{
            mapaUsuarioLibros[editor] = 1
        }

        if(limiteLibros <= mapaUsuarioLibros[editor]!!){
            recomendacion.creador().eliminarAmigo(editor)
        }
    }

    fun cambiarLimite(limite: Int): Unit { limiteLibros = limite }

    fun getCantidadLibros(usuario: Usuario) = mapaUsuarioLibros[usuario]

}

class ValoracionAutomatica(val recomendacion: Recomendacion) : AddLibrosObserver {
    override fun libroAgregado(editor: Usuario, nuevoLibro: Libro) {
        if( editor !== recomendacion.creador() && !recomendacion.usuarioValoro(editor) ) {
            recomendacion.agregarValoracion(Valoracion(5, "Excelente 100% recomendable", editor))
        }
    }
}