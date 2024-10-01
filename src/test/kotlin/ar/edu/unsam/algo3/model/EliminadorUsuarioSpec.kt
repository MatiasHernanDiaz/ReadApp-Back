
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

    val usuario = Usuario(
        nombre = "Marty",
        apellido = "McFly",
        userName = "Condensador",
        email = "volverAlFuturo@gmail.com",
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        perfil = Leedor(),
        lenguajeNativo = Lenguaje.ESPANIOL,
        palabrasXMinuto = 30,
        amigos = mutableSetOf(),
        librosLeidos = mutableListOf()
    )

    val usuario2 = Usuario(
        nombre = "Martin",
        apellido = "Martillo",
        userName = "Flujo",
        email = "volverAlFuturo2@gmail.com",
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        perfil = Leedor(),
        lenguajeNativo = Lenguaje.ESPANIOL,
        palabrasXMinuto = 30,
        amigos = mutableSetOf(),
        librosLeidos = mutableListOf()
    )

    val usuario3 = Usuario(
        nombre = "Felix",
        apellido = "Cat",
        userName = "Felix_el_Gato",
        email = "felixCat@gmail.com",
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        perfil = Leedor(),
        lenguajeNativo = Lenguaje.ESPANIOL,
        palabrasXMinuto = 30,
        amigos = mutableSetOf(),
        librosLeidos = mutableListOf()
    )
    //ACT
    repositorioUsuarios.limpiarParaTest()
    repositorioUsuarios.apply {
        crearItem(usuario)
        crearItem(usuario2)
        crearItem(usuario3)
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
            usuario.agregarAmigo(usuario2)
            repositorioUsuarios.ejecutarAccion(eliminarInactivos)
            repositorioUsuarios.items().size shouldBe 1
        }
        it("Un usuario hace una recomendacion"){
            usuario.agregarLibroLeido(libro1)
            val recomendacion1 = Recomendacion(
                creador = usuario,
                resegna = "Estos libros estan buenisimos!",
                libros = mutableSetOf(libro1))
            repositorioUsuarios.ejecutarAccion(eliminarInactivos)
            repositorioUsuarios.items().size shouldBe 1
        }
        it("Un usuario hace una valoracion de una recomendacion"){
            usuario.agregarLibroLeido(libro1)
            usuario2.agregarLibroLeido(libro1)
            val recomendacion1 = Recomendacion(
                creador = usuario,
                resegna = "Estos libros estan buenisimos!",
                libros = mutableSetOf(libro1))
            usuario2.valorarRecomendacion(recomendacion1, 5, "excelente")
            repositorioUsuarios.ejecutarAccion(eliminarInactivos)
            repositorioUsuarios.items().size shouldBe 2
        }
        it("Los 3 usuarios son activos"){
            usuario.agregarLibroLeido(libro1)
            usuario2.agregarLibroLeido(libro1)
            val recomendacion1 = Recomendacion(
                creador = usuario,
                resegna = "Estos libros estan buenisimos!",
                libros = mutableSetOf(libro1))
            usuario.agregarAmigo(usuario3)
            usuario2.valorarRecomendacion(recomendacion1, 5, "excelente")
            repositorioUsuarios.ejecutarAccion(eliminarInactivos)
            repositorioUsuarios.items().size shouldBe 3
        }
        repositorioUsuarios.limpiarParaTest()
    }
})