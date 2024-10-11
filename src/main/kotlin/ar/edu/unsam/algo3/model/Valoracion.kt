package ar.edu.unsam.algo3

// ¿Es correcto declararla como "data class"? Dos recomendaciones podrían obtener
// valoraciones iguales, pero no serían la misma.
data class Valoracion (var puntuacion: Int, var comentario: String, val autor: User){

    fun capazDeEditar(user: User): Boolean{
        return user === autor

    }
    fun editarComentario(user: User, comentario: String){
        if(capazDeEditar(user)){
            this.comentario = comentario
        }
        else{
            throw Exception("${user.displayName()} no es capaz de editar comentario")
        }
    }
    fun editarPuntuacion(user: User, puntuacion: Int){
        if(capazDeEditar(user)) {
            this.puntuacion = puntuacion
        }
        else{
            throw Exception("${user.displayName()} no es capaz de editar puntuación")
        }
    }
}