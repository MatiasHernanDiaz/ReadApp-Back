package ar.edu.unsam.algo3.repos

import ar.edu.unsam.algo3.User
import org.springframework.stereotype.Component

@Component
class UserRepository : Repository<User>() {
    override val items = mutableSetOf<User>()
    override fun searchItems(patron: String) = items.filter { patron.lowercase() in it.displayName().lowercase()
            || patron.lowercase() == it.username().lowercase() }

}

