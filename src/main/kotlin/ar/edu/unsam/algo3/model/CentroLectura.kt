package ar.edu.unsam.algo3

import ar.edu.unsam.algo3.repos.ItemRepo
import java.time.LocalDate

class CentroLectura(
    val libro: Libro,
    val listaDeUserConReserva : MutableSet<User>,
    val organizador:Organizador,
    val fechas : MutableSet<LocalDate>
) : ItemRepo {
    override var id: Int = -1

    fun costoReserva(): Float = organizador.costoReserva(this)
    fun cantMaxParicipantes(): Int = organizador.cantMaxParticipantes()

    fun reservarUsuario(user: User) {
        if(superamosUsuarios()){
            throw Exception("La lista de usuarios está llena")
        }
        listaDeUserConReserva.add(user)

    }
    fun superamosUsuarios() = listaDeUserConReserva.size >= cantMaxParicipantes()

    fun expiroPlublicacion() = (fechas.all{fecha -> fecha < LocalDate.now()}) || (superamosUsuarios())
}
