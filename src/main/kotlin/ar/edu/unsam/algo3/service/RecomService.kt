package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.dto.RecomDTO
import ar.edu.unsam.algo3.dto.RecomEditDTO
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones
import ar.edu.unsam.algo3.repos.UserRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.BeanFactoryPostProcessor

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class RecomService(
    val recomRepositorio: RepositorioRecomendaciones,
    val userRepository: UserRepository,
    @Qualifier("forceAutoProxyCreatorToUseClassProxying") private val creator: BeanFactoryPostProcessor
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

    fun editRecom(idRecom: Int, recomBody: RecomEditDTO): Recomendacion {
        val recommendation = recomRepositorio.itemById(idRecom)
            ?: throw Exception("Recomendación no encontrada")
        recommendation.titulo = recomBody.title
        recommendation.resegna = recomBody.description
        recommendation.publica = recomBody.publicIs

        recomRepositorio.updateItem(recommendation)
        return recommendation
    }


}


