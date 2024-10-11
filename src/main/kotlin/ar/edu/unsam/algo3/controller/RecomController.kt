package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.service.RecomService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"])
class RecomController(val recomService: RecomService) {

    @GetMapping("/recommendations")
    fun recommendation(@RequestParam("id") id: Int?, @RequestParam("text") text: String = "") =
        recomService.getAllRecoms(id, text).map { it.toDTO() }

    @GetMapping("/recommendations/{id}")
    fun getRecom(@PathVariable id: Int) = recomService.getRecomById(id).toDTO()

    @DeleteMapping("/recommendations/{idUser}/{idRecom}")
    fun deleteRecom(@PathVariable idUser: Int, @PathVariable idRecom: Int) =
        recomService.deleteRecomById(idUser, idRecom)

}
