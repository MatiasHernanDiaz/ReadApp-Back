package ar.edu.unsam.algo3.service


import ar.edu.unsam.algo3.Usuario
import ar.edu.unsam.algo3.repos.RepositoriosUsuarios
import org.springframework.stereotype.Service

@Service
class UserService (val userRepo: RepositoriosUsuarios){
    fun getAllUser(): MutableSet<Usuario> {
        //userRepo.crearItem(creadorRecom)
        return userRepo.items
    }

}