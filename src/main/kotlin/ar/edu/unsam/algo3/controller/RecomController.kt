package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.service.RecomService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], methods = [RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE])
class RecomController(val recomService: RecomService) {

    @GetMapping("/recommendations")
    fun recommendation(@RequestParam("userid") id: Int?, @RequestParam("text") text: String = "") =
        recomService.getAllRecoms(id, text).map { it.toDTO() }

    @GetMapping("/recommendations/{idRecom}/{userid}")
    fun getRecom(@PathVariable idRecom: Int,
                 @PathVariable userid: Int): RecomEditDTO = recomService.getRecomById(idRecom, userid).toEditDTO()

    @DeleteMapping("/recommendations/delete/{idRecom}/{idUser}")
    fun deleteRecom(@PathVariable idUser: Int, @PathVariable idRecom: Int) =
        recomService.deleteRecomById(idRecom, idUser)

    @PutMapping("/recommendations/update/{idRecom}")
    fun editableRecom(
        @PathVariable idRecom: Int,
        @RequestParam("userid") userid: Int,
        @RequestBody recomBody: RecomEditDTO
    ): RecomEditDTO {
        return recomService.editRecom(idRecom, recomBody, userid).toEditDTO()
    }

    @PostMapping("/recommendations/create/recom")
    fun createRecom(
        @RequestBody createRecomDTO: CreateRecomDTO,
    ): RecomDTO{
        return recomService.createRecom(createRecomDTO).toDTO()
    }

    @GetMapping("/recommendations/rating")
    fun canRating(@RequestParam("userid") userid: Int, @RequestParam("recomid") recomid: Int) : Boolean =
        recomService.canRating(userid, recomid)


    @PostMapping("/recommendations/create/rating")
    fun rating(@RequestParam("recomid") recomid: Int,
               @RequestBody ratingBody: RatingDTO) : RecomEditDTO =
        recomService.rating(recomid, ratingBody).toEditDTO()

    @DeleteMapping("/recommendations/delete/{userid}/{recomid}/{bookid}")
    fun deleteBookToRecom(@PathVariable("recomid") recomid: Int,
                          @PathVariable("userid") userid: Int,
                          @PathVariable("bookid") bookid: Int): RecomEditDTO = recomService.deteleBookToRecom(userid,recomid, bookid).toEditDTO()

}
