import ar.edu.unsam.algo3.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class ValoracionAutomaticaSpec : DescribeSpec({
    val libroOriginalRecom = Libro(
        titulo = "Don Quijote de la mancha",
        autor = Autor(
            "Cervantes",
            "Miguel",
            "Manco",
            LocalDate.of(1648, 1, 1),
            Lenguaje.ESPANIOL),
        paginas = 300,
        palabras = 50000,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ALEMAN, Lenguaje.PORTUGUES, Lenguaje.RUSO, Lenguaje.ITALIANO, Lenguaje.FRANCES)
    )

    val primerLibroAgregado = Libro(
        titulo = "Comedias",
        autor = Autor(
            "Cervantes",
            "Miguel",
            "Manco",
            LocalDate.of(1648, 1, 1),
            Lenguaje.ESPANIOL),
        paginas = 200,
        palabras = 10000,
        ediciones = 5,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ALEMAN, Lenguaje.PORTUGUES, Lenguaje.RUSO, Lenguaje.ITALIANO, Lenguaje.FRANCES)
    )

    val segundoLibroAgregado = Libro(
        titulo = "Cuentos",
        autor = Autor(
            "Cervantes",
            "Miguel",
            "Manco",
            LocalDate.of(1648, 1, 1),
            Lenguaje.ESPANIOL),
        paginas = 200,
        palabras = 40000,
        ediciones = 5,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ALEMAN, Lenguaje.PORTUGUES, Lenguaje.RUSO, Lenguaje.ITALIANO, Lenguaje.FRANCES)
    )

    val userEditor = User(
        firstName = "Roberto",
        lastName = "Carlos",
        username = "brazuca",
        email = "jogobonito@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf(libroOriginalRecom, primerLibroAgregado, segundoLibroAgregado)
    )

    val userCreador = User(
        firstName = "Marty",
        lastName = "McFly",
        username = "Condensador_De_Flujo",
        email = "volverAlFuturo@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 30,
        friends = mutableSetOf(userEditor),
        readBooks = mutableListOf(libroOriginalRecom, primerLibroAgregado, segundoLibroAgregado)
    )

    val recomendacionConValoracionAutomatica = Recomendacion(
        creador = userCreador,
        resegna = "Algunos libros interesantes",
        libros = mutableSetOf(libroOriginalRecom)
    ).apply { agregarAddLibrosObserver(ValoracionAutomatica(this)) }

    describe("Dadas dos ediciones consecutivas del usuarioEditor...") {
        recomendacionConValoracionAutomatica.agregarLibro(userEditor, primerLibroAgregado)

        it("La primera edición debe agregar una valoración automáticamente") {
            recomendacionConValoracionAutomatica.valoraciones().size shouldBe 1
        }

        recomendacionConValoracionAutomatica.agregarLibro(userEditor, segundoLibroAgregado)

        it("La segunda edición no debe tener ningún efecto sobre las valoraciones") {
            recomendacionConValoracionAutomatica.valoraciones().size shouldBe 1
        }
    }
})