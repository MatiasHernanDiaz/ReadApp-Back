package ar.edu.unsam.algo3

import java.time.LocalDate

// ¿Es correcto declararla como "data class"? Dos recomendaciones podrían obtener
// valoraciones iguales, pero no serían la misma.
data class Valoracion (var rating: Int, var description: String, val autor: User, val createDate: LocalDate = LocalDate.now()){

    fun capazDeEditar(user: User): Boolean{
        return user === autor

    }
    fun editarComentario(user: User, comentario: String){
        if(capazDeEditar(user)){
            this.description = comentario
        }
        else{
            throw Exception("${user.displayName()} no es capaz de editar comentario")
        }
    }
    fun editarPuntuacion(user: User, puntuacion: Int){
        if(capazDeEditar(user)) {
            this.rating = puntuacion
        }
        else{
            throw Exception("${user.displayName()} no es capaz de editar puntuación")
        }
    }


}