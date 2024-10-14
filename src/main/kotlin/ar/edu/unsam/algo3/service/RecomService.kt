package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.Valoracion
import ar.edu.unsam.algo3.dto.RatingDTO
import ar.edu.unsam.algo3.dto.RecomDTO
import ar.edu.unsam.algo3.dto.RecomEditDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.errors.BusinessException
import ar.edu.unsam.algo3.errors.NoIdException
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones
import ar.edu.unsam.algo3.repos.UserRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.BeanFactoryPostProcessor

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.client.HttpClientErrorException.NotFound

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

    fun editRecom(idRecom: Int, recomBody: RecomEditDTO, userid: Int): Recomendacion {
        val userEditor = userRepository.itemById(userid)
        val recommendation = recomRepositorio.itemById(idRecom)
            ?: throw NoIdException("Recomendación no encontrada!!")

        if(!recommendation.puedeEditar(userEditor!!)){
            throw BusinessException("El usuario no puede editar la recomendacion!!")
        }

        recommendation.titulo = recomBody.title
        recommendation.resegna = recomBody.description
        recommendation.publica = recomBody.publicIs
        recomRepositorio.updateItem(recommendation)
        return recomRepositorio.itemById(recommendation.id)!!
    }

    fun canRating(userid: Int, recomid: Int): Boolean {
        val recom = recomRepositorio.itemById(recomid)
        val user = userRepository.itemById(userid)
        return recom!!.puedeValorar(user!!)
    }

    fun rating(recomid: Int, ratingDTO: RatingDTO): RatingDTO {
        if(!canRating(ratingDTO.creatorId, recomid)){
            throw BusinessException("El usuario no puede valorar!!")
        }
        val newRating : Valoracion = Valoracion(
            rating = ratingDTO.rating,
            description = ratingDTO.description,
            autor = userRepository.itemById(ratingDTO.creatorId)!!
        )

        val recom = recomRepositorio.itemById(recomid)
        recom!!.agregarValoracion(newRating)
        return newRating.toDTO()
    }
}


