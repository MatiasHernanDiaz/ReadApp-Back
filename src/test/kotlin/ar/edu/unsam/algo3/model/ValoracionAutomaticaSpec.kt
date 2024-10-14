import ar.edu.unsam.algo3.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class ValoracionAutomaticaSpec : DescribeSpec({
    val libroOriginalRecom = Libro(
        titulo = "Don Quijote de la mancha",
        autor = Author(
            "Cervantes",
            "Miguel",
            "Manco",
            LocalDate.of(1648, 1, 1),
            Language.SPANISH),
        paginas = 300,
        palabras = 50000,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Language.ENGLISH, Language.GERMAN, Language.PORTUGUESE, Language.RUSSIAN, Language.ITALIAN, Language.FRENCH),
        fecha = 1,
        imagenURL = "ruta/a/la/imagen"
    )

    val primerLibroAgregado = Libro(
        titulo = "Comedias",
        autor = Author(
            "Cervantes",
            "Miguel",
            "Manco",
            LocalDate.of(1648, 1, 1),
            Language.SPANISH),
        paginas = 200,
        palabras = 10000,
        ediciones = 5,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Language.ENGLISH, Language.GERMAN, Language.PORTUGUESE, Language.RUSSIAN, Language.ITALIAN, Language.FRENCH),
        fecha = 1,
        imagenURL = "ruta/a/la/imagen"
    )

    val segundoLibroAgregado = Libro(
        titulo = "Cuentos",
        autor = Author(
            "Cervantes",
            "Miguel",
            "Manco",
            LocalDate.of(1648, 1, 1),
            Language.SPANISH),
        paginas = 200,
        palabras = 40000,
        ediciones = 5,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Language.ENGLISH, Language.GERMAN, Language.PORTUGUESE, Language.RUSSIAN, Language.ITALIAN, Language.FRENCH),
        fecha = 1,
        imagenURL = "ruta/a/la/imagen"
    )

    val userEditor = User(
        firstName = "Roberto",
        lastName = "Carlos",
        username = "brazuca",
        email = "jogobonito@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf(libroOriginalRecom, primerLibroAgregado, segundoLibroAgregado),
        password = "sarasa"
    )

    val userCreador = User(
        firstName = "Marty",
        lastName = "McFly",
        username = "Condensador_De_Flujo",
        email = "volverAlFuturo@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 30,
        friends = mutableSetOf(userEditor),
        readBooks = mutableListOf(libroOriginalRecom, primerLibroAgregado, segundoLibroAgregado),
        password = "sarasa"
    )

})