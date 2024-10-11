package ar.edu.unsam.algo3.repos

abstract class Repository<T:ItemRepo> {
    abstract val items: MutableSet<T>

    private fun itemExists(item: T): Boolean = items.map { it.id }.contains(item.id)

    fun createItem(item: T) {
        if (itemExists(item)) {
            throw Exception("El item ya existe en el repositorio.")
        }

        val lastId = items.maxOfOrNull { it.id }
        val newId = if(lastId != null) lastId + 1 else 1
        item.id = newId
        items.add(item)
    }

    fun updateItem(item: T) {
        if (!itemExists(item)) {
            throw Exception("No hay un item con ese ID en el repositorio.")
        }

        items.removeIf { it.id == item.id }
        items.add(item)
    }

    fun deleteItem(item: T) {
        if (!itemExists(item)) {
            throw Exception("No hay un item con ese ID en el repositorio.")
        }
        items.removeIf { it.id == item.id }
    }


    fun itemById(id: Int): T? = items.find { it.id == id }


    abstract fun searchItems(patron: String): List<T>

    fun items() = items.toList()

    fun cleanForTests() = items.clear()
}

interface ItemRepo {
    var id: Int
}
