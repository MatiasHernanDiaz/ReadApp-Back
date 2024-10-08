package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones

import org.springframework.stereotype.Service

@Service
class RecomService (val recomRepositorio: RepositorioRecomendaciones){
    fun getAllRecoms(id:Int?): List<Recomendacion> = recomRepositorio.items().filter{ id === null || it.creador.id == id  }


    fun getRecomById(id: Int): Recomendacion = recomRepositorio.itemPorId(id)!!

}