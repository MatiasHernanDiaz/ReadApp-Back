package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.Recomendacion
import ar.edu.unsam.algo3.Repositorio
import org.springframework.stereotype.Service

@Service
class RecomService (val recomRepositorio: Repositorio<Recomendacion>){
    fun getAllRecoms() = recomRepositorio.items()
}