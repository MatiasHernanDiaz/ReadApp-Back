import ar.edu.unsam.algo3.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import java.time.LocalDate

class ActualizadorLibrosSpec : DescribeSpec({
    //AUTORES

    val autor1 = Autor(
        nombre = "autor1",
        apellido = "apellidoAutor1",
        seudonimo = "king",
        idiomaNativo = Lenguaje.ESPANIOL,
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        premios = mutableListOf()
    )

    val autor2 = Autor(
        nombre = "autor2",
        apellido = "apellidoAutor2",
        seudonimo = "king",
        idiomaNativo = Lenguaje.INGLES,
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        premios = mutableListOf()
    )

    val autor3 = Autor(
        nombre = "autor3",
        apellido = "apellidoAutor3",
        seudonimo = "king",
        idiomaNativo = Lenguaje.FRANCES,
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        premios = mutableListOf()
    )

    //LIBROS
    val libro1 = Libro(
        titulo = "Titulo1",
        autor = autor1,
        imageURL = "",
        paginas = 180,
        palabras = 40_000,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES)
    )

    val libro2 = Libro(
        titulo = "Titulo2",
        autor = autor2,
        imageURL = "",
        paginas = 180,
        palabras = 40_000,
        ediciones = 2,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Lenguaje.INGLES)
    )

    val libro3 = Libro(
        titulo = "Titulo3",
        autor = autor3,
        imageURL = "",
        paginas = 180,
        palabras = 40_000,
        ediciones = 3,
        ventasSemanales = 10001,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES)
    )

    repositorioLibros.limpiarParaTest()

    val mockedEnviaMail = mockk<EnviaMail>(relaxUnitFun = true)

    val actualizadorLibros = ActualizadorLibros().apply {
        enviadorMail = mockedEnviaMail
    }

    repositorioLibros.service = stubServiceLibros()

    describe("Se agregan tres libros para ser actualizados mediante la acción ActualizadorLibros") {
        // Act
        repositorioLibros.crearItem(libro1)
        repositorioLibros.crearItem(libro2)
        repositorioLibros.crearItem(libro3)

        it("Previamente, el libro dos no es best seller") {
            libro2.esBestSeller() shouldBe false
        }

        repositorioLibros.ejecutarAccion(actualizadorLibros)

        it("Luego de la actualización, el libro 2 debe ser best seller") {
            libro2.esBestSeller() shouldBe true
        }
    }
})