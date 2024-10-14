package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.RatingDTO
import ar.edu.unsam.algo3.dto.RecomDTO
import ar.edu.unsam.algo3.dto.RecomEditDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.service.RecomService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], methods = [RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET])
class RecomController(val recomService: RecomService) {

    @GetMapping("/recommendations")
    fun recommendation(@RequestParam("userid") id: Int?, @RequestParam("text") text: String = "") =
        recomService.getAllRecoms(id, text).map { it.toDTO() }

    @GetMapping("/recommendations/{idRecom}")
    fun getRecom(@PathVariable idRecom: Int) = recomService.getRecomById(idRecom).toDTO()

    @DeleteMapping("/recommendations/{idUser}/{idRecom}")
    fun deleteRecom(@PathVariable idUser: Int, @PathVariable idRecom: Int) =
        recomService.deleteRecomById(idUser, idRecom)

    @PutMapping("/recommendations/update/{idRecom}")
    fun editableRecom(
        @PathVariable idRecom: Int,
        @RequestParam("userid") userid: Int,
        @RequestBody recomBody: RecomEditDTO
    ): RecomDTO {
        return recomService.editRecom(idRecom, recomBody, userid).toDTO()
    }

    @GetMapping("/recommendations/rating")
    fun canRating(@RequestParam("userid") userid: Int, @RequestParam("recomid") recomid: Int) : Boolean =
        recomService.canRating(userid, recomid)

    @PostMapping("/recommendations/rating/create")
    fun rating(@RequestParam("recomid") recomid: Int,
               @RequestBody ratingBody: RatingDTO) : RatingDTO =
        recomService.rating(recomid, ratingBody)

}
