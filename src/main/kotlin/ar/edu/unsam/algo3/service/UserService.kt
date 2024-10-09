package ar.edu.unsam.algo3.service


import ar.edu.unsam.algo3.User
import ar.edu.unsam.algo3.repos.RepositoriosUsuarios
import org.springframework.stereotype.Service

@Service
class UserService (val userRepo: RepositoriosUsuarios){
    fun getAllUser(): MutableSet<User> {
        //userRepo.crearItem(creadorRecom)
        return userRepo.items
    }

    fun editProfile(user: User): User? {
        userRepo.actualizarItem(user)

        return userRepo.itemPorId(user.id)
    }

}