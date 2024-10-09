
import ar.edu.unsam.algo3.*
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate


class LimitadorLibrosAgregadosSpec: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    // Arrange
    val amigoDelCreador = User(
        firstName = "Juan Luis",
        lastName = "Guerra",
        username = "pez",
        email = "pez@gmail.com",
        birthday = LocalDate.of(1988, 8, 15),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 300,
        friends = mutableSetOf()
    )

    val otroAmigoDelCreador = User(
        firstName = "Rubén",
        lastName = "Rada",
        username = "negro",
        email = "negrorada@gmail.com",
        birthday = LocalDate.of(1958, 2, 15),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 300,
        friends = mutableSetOf()
    )

    val creadorRecom = User(
        firstName = "Pedro",
        lastName = "Picapiedras",
        username = "piedra",
        email = "piedra@gmail.com",
        birthday = LocalDate.of(1990, 8, 24),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 300,
        friends = mutableSetOf(amigoDelCreador, otroAmigoDelCreador)
    )

    val autor = Autor(
        nombre = "Jorge Luis",
        apellido = "Borges",
        seudonimo = "cieguito",
        idiomaNativo = Lenguaje.ESPANIOL,
        fechaNacimiento = LocalDate.of(1978, 6, 1)
    )

    val libro1 = Libro(
        titulo = "Aleph",
        autor = autor,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES),
    )

    val libro2 = Libro(
        titulo = "Aleph",
        autor = autor,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES),
    )

    val libro3 = Libro(
        titulo = "Aleph",
        autor = autor,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES),
    )

    val libro4 = Libro(
        titulo = "Aleph",
        autor = autor,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES),
    )

    creadorRecom.apply {
        agregarLibroLeido(libro1)
        agregarLibroLeido(libro2)
        agregarLibroLeido(libro3)
        agregarLibroLeido(libro4)
    }

    amigoDelCreador.apply {
        agregarLibroLeido(libro1)
        agregarLibroLeido(libro2)
        agregarLibroLeido(libro3)
        agregarLibroLeido(libro4)
    }

    otroAmigoDelCreador.apply {
        agregarLibroLeido(libro1)
        agregarLibroLeido(libro2)
        agregarLibroLeido(libro3)
        agregarLibroLeido(libro4)
    }

    val recomendacion = Recomendacion(
        creador = creadorRecom,
        resegna = "Estos libros están buenísimos!",
        libros = mutableSetOf(libro4)
    )

    val limitadorLibrosObserver = LimitadorLibrosAgregados(recomendacion = recomendacion, limiteLibros = 2)
    recomendacion.agregarAddLibrosObserver(limitadorLibrosObserver)

    describe("Dado un amigo del creador") {
        it("Agrega un libro"){
            //act
            recomendacion.agregarLibro(amigoDelCreador, libro1)
            //assert
            limitadorLibrosObserver.getCantidadLibros(amigoDelCreador) shouldBe 1
        }
    }
    describe("Dado dos amigos del creador"){
        //act
        recomendacion.agregarLibro(amigoDelCreador, libro1)
        recomendacion.agregarLibro(amigoDelCreador, libro2)
        recomendacion.agregarLibro(otroAmigoDelCreador, libro3)

        it("Agregan varios libros"){
            limitadorLibrosObserver.getCantidadLibros(amigoDelCreador) shouldBe 2
            limitadorLibrosObserver.getCantidadLibros(otroAmigoDelCreador) shouldBe 1
        }
        it("Un amigo alcanza el limite de libros y ya no es amigo"){
            creadorRecom.esAmigo(amigoDelCreador) shouldBe false
            creadorRecom.esAmigo(otroAmigoDelCreador) shouldBe true
        }
    }
})