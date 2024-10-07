package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones

import org.springframework.stereotype.Service

@Service
class RecomService (val recomRepositorio: RepositorioRecomendaciones){
    //Aca tenes un QUILOMBASO de recursividad y toda la chota, fijate como lo arresglas Gas
    //Fijate que te deje un @JSONIgnore en usuarios en la recoms, y eso medio que corto tod0... abria que checkearlo bn
    //son comentarios que hice en distintos horarios
    fun getAllRecoms(): List<Recomendacion> {
        //recomRepositorio.crearItem(recomCompleta)
        return recomRepositorio.items()
    }

    fun getRecomById(id: Int): Recomendacion = recomRepositorio.itemPorId(id)!!

}