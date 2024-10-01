package ar.edu.unsam.algo3

import java.time.LocalDate

class CentroLectura(
    val libro: Libro,
    val listaDeUsuarioConReserva : MutableSet<Usuario>,
    val organizador:Organizador,
    val fechas : MutableSet<LocalDate>
) : ItemRepo {
    override var id: UInt? = null

    fun costoReserva(): Float = organizador.costoReserva(this)
    fun cantMaxParicipantes(): Int = organizador.cantMaxParticipantes()

    fun reservarUsuario(usuario: Usuario) {
        if(superamosUsuarios()){
            throw Exception("La lista de usuarios está llena")
        }
        listaDeUsuarioConReserva.add(usuario)

    }
    fun superamosUsuarios() = listaDeUsuarioConReserva.size >= cantMaxParicipantes()

    fun expiroPlublicacion() = (fechas.all{fecha -> fecha < LocalDate.now()}) || (superamosUsuarios())
}
