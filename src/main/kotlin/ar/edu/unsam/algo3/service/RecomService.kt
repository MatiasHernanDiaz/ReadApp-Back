package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.model.recomCompleta
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones

import org.springframework.stereotype.Service

@Service
class RecomService (val recomRepositorio: RepositorioRecomendaciones){
    //Aca tenes un QUILOMBASO de recursividad y toda la chota, fijate como lo arresglas Gas
    fun getAllRecoms(): List<Recomendacion> {
        recomRepositorio.crearItem(recomCompleta)
        return recomRepositorio.items()
    }

}