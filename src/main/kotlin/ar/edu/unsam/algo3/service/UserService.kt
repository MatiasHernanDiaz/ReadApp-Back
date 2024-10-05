package ar.edu.unsam.algo3.service


import ar.edu.unsam.algo3.Usuario
import ar.edu.unsam.algo3.repos.Repositorio
import org.springframework.stereotype.Service

@Service
class UserService (val userRepositorio: Repositorio<Usuario>){
    fun getAllRecoms() = userRepositorio.items()

}