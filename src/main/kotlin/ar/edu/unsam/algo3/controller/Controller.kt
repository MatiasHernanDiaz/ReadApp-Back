package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.repositorioRecomendaciones
import ch.qos.logback.core.status.Status
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @GetMapping("/{repository}/{action}/{id}")
    fun defaultGet(id): String {
//        val recom = repositorioRecomendaciones.itemPorId(id)
//        repositorioRecomendaciones.eliminarItem(recom)
    }

    @DeleteMapping("/recommendations/delete/:id_recom")


}