package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.toEditProfileDTO
import ar.edu.unsam.algo3.service.RecomService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RecomController (val recomService: RecomService){

    @GetMapping("/recommendations")
    fun recommendation() = recomService.getAllRecoms().map{it.toEditProfileDTO()}
}
