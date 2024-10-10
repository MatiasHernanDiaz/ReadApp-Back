import ar.edu.unsam.algo3.*
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import java.time.LocalDate

class RegistroAportesSpec: DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    val autor = Autor(
        nombre = "Jorge Luis",
        apellido = "Borges",
        seudonimo = "cieguito",
        idiomaNativo = Language.SPANISH,
        fechaNacimiento = LocalDate.of(1978, 6, 1)
    )
    val libro1 = Libro(titulo = "Aleph",
        autor = autor,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Language.ENGLISH),)
    val libro2 = Libro(titulo = "Rayuela",
        autor = autor,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Language.ENGLISH),)

    val amigoCreador = User(
        firstName = "Martin",
        lastName = "Esperanza",
        username = "tiburon",
        email = "tiburon@hotmail.com",
        birthday = LocalDate.of(1958, 10, 30),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 200,
        readBooks = mutableListOf(libro1,libro2),
        friends = mutableSetOf(),
        password = "sarasa")

    val creador = User(
        firstName = "Juan Luis",
        lastName = "Guerra",
        username = "pez",
        email = "pez@gmail.com",
        birthday = LocalDate.of(1998, 8, 15),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg =  300,
        friends = mutableSetOf(),
        readBooks = mutableListOf(libro1,libro2),
        booksToRead = mutableSetOf(),
        password = "sarasa"
    )


    val recomendacion = Recomendacion(
        creador = creador,
        resegna = "Estos libros están buenísimos!",
        libros = mutableSetOf()
    )
})

