package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.User
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
    val loginService: LoginService,
    @Qualifier("forceAutoProxyCreatorToUseClassProxying") private val creator: BeanFactoryPostProcessor
) {
    fun getAllRecoms(id: Int?): List<Recomendacion> =
        recomRepositorio.items().filter { id === null || it.creador.id == id }

    fun getAllRecoms(id: Int?, text: String = ""): List<Recomendacion> {
        if( id === null ) {
            return recomRepositorio.searchItems(text).filter { loginService.getSignedUser()?.isRecommendable(it) ?: false }
        } else {
            return recomRepositorio.searchItems(text).filter { it.creador.id == id }
        }
    }

    fun getRecomById(id: Int, userid: Int): Recomendacion {
        val recom = recomRepositorio.itemById(id, "No se encontro recomendacion")
        val user = userRepository.itemById(userid, "No se encontro usuario")
        setCarEditAndCanRating(recom, user)
        return recom
    }

    fun deleteRecomById(idRecom: Int, idUser: Int) {
        val recomToDelete = getRecomById(idRecom, idUser)
        val userOwner = userRepository.itemById(idUser)

        if (recomToDelete.creador.id == idUser) {
            userOwner.removeRecomendation(recomToDelete)
            recomRepositorio.deleteItem(recomToDelete)
        } else {
            throw Exception("El usuario no es el propietario de la recomendacion o no existe.")
        }
    }

    fun editRecom(idRecom: Int, recomBody: RecomEditDTO, userid: Int): Recomendacion {
        val userEditor = userRepository.itemById(userid, "Usuario no encontrado")

        val recommendation = recomRepositorio.itemById(idRecom, "No se encuentra la recomendacion")

        if(!recommendation.puedeEditar(userEditor)){
            throw BusinessException("El usuario no puede editar la recomendacion!!")
        }

        setRecom( recommendation, recomBody)
        recomRepositorio.updateItem(recommendation)
        return recomRepositorio.itemById(recommendation.id, "No se encontro recomendacion")
    }

    fun canRating(userid: Int, recomid: Int): Boolean {
        val recom = recomRepositorio.itemById(recomid, "No se encuentra la recomendacion")
        val user = userRepository.itemById(userid, "No se encontro usuario logeado")
        return recom.puedeValorar(user)
    }

    fun rating(recomid: Int, ratingDTO: RatingDTO): Recomendacion {

        if(!canRating(ratingDTO.creatorId, recomid)){
            throw BusinessException("El usuario no puede valorar!!")
        }
        val newRating = Valoracion(
            rating = ratingDTO.rating,
            description = ratingDTO.description,
            autor = userRepository.itemById(ratingDTO.creatorId)
        )

        val recom = recomRepositorio.itemById(recomid, "No se encontro recomendacion")
        recom.agregarValoracion(newRating)
        return recom
    }

    fun createRecom(createRecomDTO: CreateRecomDTO): Recomendacion {
        val id = recomRepositorio.createItem(createRecomAux(createRecomDTO))
        return recomRepositorio.itemById(id, "No se encontro recomendacion")
    }

    private fun setRecom( recommendation: Recomendacion, recomBody: RecomEditDTO){
        recommendation.titulo = recomBody.title
        recommendation.resegna = recomBody.description
        recommendation.publica = recomBody.publicIs
    }

    private fun setCarEditAndCanRating(recom: Recomendacion, user: User){
        recom.canRating = recom.puedeValorar(user)
        recom.canEdit = recom.puedeEditar(user)
    }

    private fun createRecomAux(createRecomDTO: CreateRecomDTO) : Recomendacion{
        val creator = userRepository.itemById(createRecomDTO.userid, "No se encontro usuario")
        val newRecom = Recomendacion(titulo = createRecomDTO.title,
            creador = creator,
            libros = mutableSetOf(),
            resegna = "Dile a los demas de que se trata..."
        )
        return newRecom
    }
}


