package ar.edu.unsam.algo3

import ar.edu.unsam.algo3.repos.*

interface AccionRepositorio {

    val nombreProceso: String
    var enviadorMail: EnviaMail

    fun ejecutar() {
        ejecutarAccion()
        enviarMail()
    }

    fun ejecutarAccion()

    fun enviarMail(): Unit{
        val mail = Mail(
            from = "Notificaciones@readapp.com.ar",
            to = "admin@readapp.com.ar",
            subject = "Ejecución de ${nombreProceso}",
            content = "Se realizó el proceso: ${nombreProceso}"
        )
        enviadorMail.envioMail(mail)
    }
}

class EliminadorUsuario() : AccionRepositorio{

    override val nombreProceso = "Eliminador de usuarios"
    override lateinit var enviadorMail: EnviaMail
    val repositorioUsuarios: RepositoriosUsuarios = RepositoriosUsuarios()

    override fun ejecutarAccion() {
        repositorioUsuarios.items().filter { !it.tieneValoraciones() && !it.tieneRecomendaciones()
                && !esConcideradoAmigoPorOtro(it, repositorioUsuarios)  }.forEach({repositorioUsuarios.eliminarItem(it)})
    }

    private fun esConcideradoAmigoPorOtro(usuario: Usuario, repo: Repositorio<Usuario>): Boolean = repo.items().any({it.esAmigo(usuario)})
}

class ActualizadorLibros : AccionRepositorio{
    override val nombreProceso = "Actualizador de libros"
    override lateinit var enviadorMail: EnviaMail
    val repositorioLibros: RepositorioLibros = RepositorioLibros()
    override fun ejecutarAccion() {
        //THEO
        repositorioLibros.actualizarLibros()
    }
}

class EliminadorCentroLectura(): AccionRepositorio {
    override val nombreProceso = "Eliminador de centros expirados"
    override lateinit var enviadorMail: EnviaMail
    val repositorioCentroLectura: RepositorioCentroLectura = RepositorioCentroLectura()

    override fun ejecutarAccion() {
        repositorioCentroLectura.items().filter { it.expiroPlublicacion() }
        .forEach { repositorioCentroLectura.eliminarItem(it) }
    }
}

class AgregadorAutores(val listaAutores: List<Autor>) : AccionRepositorio{
    override val nombreProceso = "Agregador de autores"
    override lateinit var enviadorMail: EnviaMail
    val repositorioAutores: RepositorioAutores = RepositorioAutores()
    override fun ejecutarAccion() {
        listaAutores.forEach { repositorioAutores.crearItem(it) }
    }
}