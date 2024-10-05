package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.*
import ar.edu.unsam.algo3.repos.RepositorioAutores
import ar.edu.unsam.algo3.repos.RepositorioLibros
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones
import ar.edu.unsam.algo3.repos.RepositoriosUsuarios
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DataInitializer(
    @Autowired val repoBook: RepositorioLibros,
    @Autowired val repoAutor: RepositorioAutores,
    @Autowired val repoRecom: RepositorioRecomendaciones,
    @Autowired val repoUser: RepositoriosUsuarios
) : CommandLineRunner {

    override fun run(vararg args: String?) {

        val autorPreferido = Autor(
            nombre = "Jorge Luis",
            apellido = "Borges",
            seudonimo = "cieguito",
            idiomaNativo = Lenguaje.ESPANIOL,
            fechaNacimiento = LocalDate.of(1978, 6, 1)
        )
        val otroAutorPreferido = Autor(
            nombre = "Julio",
            apellido = "Cortázar",
            seudonimo = "Julito",
            idiomaNativo = Lenguaje.INGLES,
            fechaNacimiento = LocalDate.of(1978, 6, 1)
        )
        val autorNoPreferido = Autor(
            nombre = "Bernardo",
            apellido = "Stamateas",
            seudonimo = "berni",
            idiomaNativo = Lenguaje.ESPANIOL,
            fechaNacimiento = LocalDate.of(1978, 6, 1)
        )

        repoAutor.crearItem(autorPreferido)
        repoAutor.crearItem(otroAutorPreferido)
        repoAutor.crearItem(autorNoPreferido)

        val libroAutorPreferido = Libro(
            titulo = "Aleph",
            autor = autorPreferido,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES),
        )

        val libroOtroAutorPreferido = Libro(
            titulo = "Rayuela",
            autor = otroAutorPreferido,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES),
        )

        val libroAutorNoPreferido = Libro(
            titulo = "Sarasa",
            autor = autorNoPreferido,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES)
        )

        repoBook.crearItem(libroAutorPreferido)
        repoBook.crearItem(libroOtroAutorPreferido)
        repoBook.crearItem(libroAutorNoPreferido)

        val amigoDelCreador = Usuario(
            nombre = "Juan Luis",
            apellido = "Guerra",
            userName = "pez",
            email = "pez@gmail.com",
            fechaNacimiento = LocalDate.of(1988, 8, 15),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 300,
            amigos = mutableSetOf()
        )

        val otroAmigoDelCreador = Usuario(
            nombre = "Rubén",
            apellido = "Rada",
            userName = "negro",
            email = "negrorada@gmail.com",
            fechaNacimiento = LocalDate.of(1958, 2, 15),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 300,
            amigos = mutableSetOf()
        )

        val usuarioCualquiera = Usuario(
            nombre = "Leonor",
            apellido = "Benedetto",
            userName = "leona",
            email = "leo@gmail.com",
            fechaNacimiento = LocalDate.of(1978, 8, 25),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 300,
            amigos = mutableSetOf()
        )
        val creadorRecom = Usuario(
            nombre = "Pedro",
            apellido = "Picapiedras",
            userName = "piedra",
            email = "piedra@gmail.com",
            fechaNacimiento = LocalDate.of(1990, 8, 24),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 300,
            amigos = mutableSetOf(amigoDelCreador, otroAmigoDelCreador),
            librosLeidos = mutableListOf(libroAutorPreferido, libroOtroAutorPreferido, libroAutorNoPreferido)
        )

        repoUser.crearItem(amigoDelCreador)
        repoUser.crearItem(otroAmigoDelCreador)
        repoUser.crearItem(usuarioCualquiera)
        repoUser.crearItem(creadorRecom)



        val valoracion = Valoracion(puntuacion = 5, comentario = "Buenisimo Bro!", autor = usuarioCualquiera)

        val recomCompleta = Recomendacion(
            creador = creadorRecom,
            resegna = "Estos libros están buenísimos!",
            libros = mutableSetOf(libroAutorPreferido, libroOtroAutorPreferido, libroAutorNoPreferido)
        )

        val recomCompleta2 = Recomendacion(
            creador = amigoDelCreador,
            resegna = "Estos libros están buenísimos 2222!",
            libros = mutableSetOf()
        )

        val recomCompleta3 = Recomendacion(
            creador = creadorRecom,
            resegna = "Estos libros están buenísimos3333!",
            libros = mutableSetOf()
        )

        val recomCompleta4 = Recomendacion(
            creador = creadorRecom,
            resegna = "Estos libros están buenísimos 4444!",
            libros = mutableSetOf()
        )

        repoRecom.crearItem(recomCompleta)
        repoRecom.crearItem(recomCompleta2)
        repoRecom.crearItem(recomCompleta3)
        repoRecom.crearItem(recomCompleta4)
    }
}