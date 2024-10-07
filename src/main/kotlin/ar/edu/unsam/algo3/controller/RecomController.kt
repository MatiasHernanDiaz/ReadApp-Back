package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.service.RecomService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RecomController (val recomService: RecomService){

    @CrossOrigin(origins = ["*"])
    @GetMapping("/recommendations")
    fun recommendation() = recomService.getAllRecoms().map{it.toDTO()}

    @CrossOrigin(origins = ["*"])
    @GetMapping("/recommendations/{id}")
    fun getRecom(@PathVariable id: Int) = recomService.getRecomById(id).toDTO()

}
