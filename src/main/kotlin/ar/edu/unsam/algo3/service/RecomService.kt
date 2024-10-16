package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.Valoracion
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.errors.BusinessException
import ar.edu.unsam.algo3.errors.NoIdException
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones
import ar.edu.unsam.algo3.repos.UserRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.BeanFactoryPostProcessor

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.client.HttpClientErrorException.NotFound
import kotlin.jvm.Throws

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

    fun getRecomById(id: Int, userid: Int): Recomendacion {
        val recom = recomRepositorio.itemById(id)
        val user = userRepository.itemById(userid)
        if(recom == null ){
            throw NoIdException("Recomendacion no encontrada") //mover al repo
        }
        if(user == null ){
            throw NoIdException("Usuario logeado no encontrado") //mover al repo
        }
        recom.canRating = recom.puedeValorar(user)
        recom.canEdit = recom.puedeEditar(user)

        return recom
    }

    fun deleteRecomById(idRecom: Int, idUser: Int) {
        val recomToDelete = getRecomById(idRecom, idUser)
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
        print("Puede valorar?? " + recom!!.puedeValorar(user!!))
        return recom.puedeValorar(user)
    }

    fun rating(recomid: Int, ratingDTO: RatingDTO): Recomendacion {

        if(!canRating(ratingDTO.creatorId, recomid)){
            throw BusinessException("El usuario no puede valorar!!")
        }
        val newRating = Valoracion(
            rating = ratingDTO.rating,
            description = ratingDTO.description,
            autor = userRepository.itemById(ratingDTO.creatorId)!!
        )

        val recom = recomRepositorio.itemById(recomid)

        if(recom === null){
            throw NoIdException("No hay una recomendacion con este ID")
        }
        recom.agregarValoracion(newRating)

        return recom
    }

    fun createRecom(createRecomDTO: CreateRecomDTO): RecomDTO {
        val creator = userRepository.itemById(createRecomDTO.userid)
        val newRecom = Recomendacion(titulo = createRecomDTO.title,
            creador = creator!!,
            libros = mutableSetOf(),
            resegna = ""
            )
        val id = recomRepositorio.createItem(newRecom)
        return recomRepositorio.itemById(id)!!.toDTO()
    }
}


