package ar.edu.unsam.algo3

// ¿Es correcto declararla como "data class"? Dos recomendaciones podrían obtener
// valoraciones iguales, pero no serían la misma.
data class Valoracion (private var puntuacion: Int, private var comentario: String, private val autor: Usuario){

    fun getComentario() = comentario

    fun getAutor() = autor

    fun getPuntuacion() = puntuacion

    fun capazDeEditar(usuario: Usuario): Boolean{
        return usuario === autor

    }
    fun editarComentario(usuario: Usuario, comentario: String){
        if(capazDeEditar(usuario)){
            this.comentario = comentario
        }
        else{
            throw Exception("${usuario.nombreCompleto()} no es capaz de editar comentario")
        }
    }
    fun editarPuntuacion(usuario: Usuario,puntuacion: Int){
        if(capazDeEditar(usuario)) {
            this.puntuacion = puntuacion
        }
        else{
            throw Exception("${usuario.nombreCompleto()} no es capaz de editar puntuación")
        }
    }
}