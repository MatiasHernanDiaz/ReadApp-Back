import ar.edu.unsam.algo3.*
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class RegistroAportesSpec: DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    val autor = Autor(
        nombre = "Jorge Luis",
        apellido = "Borges",
        seudonimo = "cieguito",
        idiomaNativo = Lenguaje.ESPANIOL,
        fechaNacimiento = LocalDate.of(1978, 6, 1)
    )
    val libro1 = Libro(titulo = "Aleph",
        autor = autor,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES),)
    val libro2 = Libro(titulo = "Rayuela",
        autor = autor,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES),)

    val amigoCreador = User(
        firstName = "Martin",
        lastName = "Esperanza",
        username = "tiburon",
        email = "tiburon@hotmail.com",
        birthday = LocalDate.of(1958, 10, 30),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 200,
        readBooks = mutableListOf(libro1,libro2),
        friends = mutableSetOf())

    val creador = User(
        "Juan Luis",
        "Guerra",
        "pez",
        "pez@gmail.com",
        LocalDate.of(1998, 8, 15),
        GreatReader(),
        Lenguaje.ESPANIOL,
        300,
        mutableSetOf(),
        mutableListOf(libro1,libro2)
    )


    val observer = RegistroAportes()
    val recomendacion = Recomendacion(
        creador = creador,
        resegna = "Estos libros están buenísimos!",
        libros = mutableSetOf()
    )

    describe("el creador agrega todos los libros"){
        recomendacion.agregarAddLibrosObserver(observer)
        it("el creador agrega un libro y no hay problema"){
            recomendacion.agregarLibro(creador,libro1)
            observer.cantAportes(creador) shouldBe 1
        }
        it("el creador agregó mas de un libro y la cantidad de aportes cambia"){
            recomendacion.agregarLibro(creador,libro1)
            recomendacion.agregarLibro(creador,libro2)
            observer.cantAportes(creador) shouldBe 2
        }
    }
    describe("el amigo tambien participa"){
        amigoCreador.agregarAmigo(creador)
        creador.agregarAmigo(amigoCreador)
        recomendacion.agregarAddLibrosObserver(observer)

        it("el creador agrega un libro y el amigo otro"){
            recomendacion.agregarLibro(creador,libro1)
            recomendacion.agregarLibro(amigoCreador,libro2)
            observer.cantAportes(creador) shouldBe 1
            observer.cantAportes(amigoCreador) shouldBe 1
        }
        it("los aportes de cada creador son distintos"){
            recomendacion.agregarLibro(creador,libro1)
            recomendacion.agregarLibro(amigoCreador,libro2)
            observer.librosAportadosPor(creador) shouldBe setOf(libro1)
            observer.librosAportadosPor(amigoCreador) shouldBe setOf(libro2)
        }
    }
})

