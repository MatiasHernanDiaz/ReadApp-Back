package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones
import ar.edu.unsam.algo3.repos.UserRepository

import org.springframework.stereotype.Service

@Service
class RecomService(
    val recomRepositorio: RepositorioRecomendaciones,
    val userRepository: UserRepository
) {
    fun getAllRecoms(id: Int?): List<Recomendacion> =
        recomRepositorio.items().filter { id === null || it.creador.id == id }

    fun getAllRecoms(id: Int?, text: String = ""): List<Recomendacion> =
        recomRepositorio.searchItems(text).filter { id === null || it.creador.id == id }

    fun getRecomById(id: Int): Recomendacion = recomRepositorio.itemById(id)!!

    fun deleteRecomById(idUser: Int, idRecom: Int) {
        val recomToDelete = getRecomById(idRecom)
        val userOwner = userRepository.itemById(idUser)

        if (userOwner != null && recomToDelete.creador.id == idUser) {
            userOwner.removeRecomendation(recomToDelete)
            recomRepositorio.deleteItem(recomToDelete)
        } else {
            throw Exception("El usuario no es el propietario de la recomendación o no existe.")
        }
    }

}


