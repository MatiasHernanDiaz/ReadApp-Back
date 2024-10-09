
import ar.edu.unsam.algo3.*
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import java.time.LocalDate

class EliminadorUsuarioSpec : DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    val autor1 = Autor(
        nombre = "autor1",
        apellido = "apellidoAutor1",
        seudonimo = "king",
        idiomaNativo = Lenguaje.ESPANIOL,
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        premios = mutableListOf()
    )

    //LIBROS

    val libro1 = Libro(
        titulo = "Titulo1",
        autor = autor1,
        paginas = 180,
        palabras = 40_000,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES)
    )

    //USUARIOS

    val user = User(
        firstName = "Marty",
        lastName = "McFly",
        username = "Condensador",
        email = "volverAlFuturo@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf()
    )

    val user2 = User(
        firstName = "Martin",
        lastName = "Martillo",
        username = "Flujo",
        email = "volverAlFuturo2@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf()
    )

    val user3 = User(
        firstName = "Felix",
        lastName = "Cat",
        username = "Felix_el_Gato",
        email = "felixCat@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf()
    )
    //ACT
    repositorioUsuarios.limpiarParaTest()
    repositorioUsuarios.apply {
        crearItem(user)
        crearItem(user2)
        crearItem(user3)
    }
    val eliminarInactivos = EliminadorUsuario()

    val mockedEnviaMail = mockk<EnviaMail>(relaxUnitFun = true)

    eliminarInactivos.enviadorMail = mockedEnviaMail

    describe("Dado un repositorio con 3 usuarios"){
        it("No tiene recomendacione, valoracion, no son conciderados amigos de otros"){
            repositorioUsuarios.ejecutarAccion(eliminarInactivos)
            repositorioUsuarios.items().size shouldBe 0
        }
        it("Un usuario es conciderado amigo de otro"){
            //act
            user.agregarAmigo(user2)
            repositorioUsuarios.ejecutarAccion(eliminarInactivos)
            repositorioUsuarios.items().size shouldBe 1
        }
        it("Un usuario hace una recomendacion"){
            user.agregarLibroLeido(libro1)
            val recomendacion1 = Recomendacion(
                creador = user,
                resegna = "Estos libros estan buenisimos!",
                libros = mutableSetOf(libro1))
            repositorioUsuarios.ejecutarAccion(eliminarInactivos)
            repositorioUsuarios.items().size shouldBe 1
        }
        it("Un usuario hace una valoracion de una recomendacion"){
            user.agregarLibroLeido(libro1)
            user2.agregarLibroLeido(libro1)
            val recomendacion1 = Recomendacion(
                creador = user,
                resegna = "Estos libros estan buenisimos!",
                libros = mutableSetOf(libro1))
            user2.valorarRecomendacion(recomendacion1, 5, "excelente")
            repositorioUsuarios.ejecutarAccion(eliminarInactivos)
            repositorioUsuarios.items().size shouldBe 2
        }
        it("Los 3 usuarios son activos"){
            user.agregarLibroLeido(libro1)
            user2.agregarLibroLeido(libro1)
            val recomendacion1 = Recomendacion(
                creador = user,
                resegna = "Estos libros estan buenisimos!",
                libros = mutableSetOf(libro1))
            user.agregarAmigo(user3)
            user2.valorarRecomendacion(recomendacion1, 5, "excelente")
            repositorioUsuarios.ejecutarAccion(eliminarInactivos)
            repositorioUsuarios.items().size shouldBe 3
        }
        repositorioUsuarios.limpiarParaTest()
    }
})