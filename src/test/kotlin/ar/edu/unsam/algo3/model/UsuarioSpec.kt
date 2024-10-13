import ar.edu.unsam.algo3.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDate
import java.time.Period


class UsuarioSpec : DescribeSpec({
    describe("dado un usuario nacido en 1968 y lee 30 palabras por min") {

        val miguel = Author(firstName = "Miguel", lastName = "de Cervantes", alias = "El manco", nativeLanguage = Language.SPANISH, birthday = LocalDate.of(1978, 6, 1), prices= mutableListOf())
        val tolkien = Author(firstName = "J. R. R.", lastName = "Tolkien", alias = "Hobbit", nativeLanguage = Language.ENGLISH,birthday = LocalDate.of(1978, 6, 1), prices= mutableListOf())

        //Arrange
        //Creacion de Lectores

        val user = User(
            firstName = "Marty",
            lastName = "McFly",
            username = "Condensador_De_Flujo",
            email = "volverAlFuturo@gmail.com",
            birthday = LocalDate.of(1968, 6, 9),
            searchCriteria = GreatReader(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 30,
            friends = mutableSetOf(),
            password = "sarasa"
        )
        val user1 = User(
            firstName = "Juan",
            lastName = "Perez",
            username = "juPerez",
            email = "jperez@gmail.com",
            birthday = LocalDate.of(1988, 1, 12),
            searchCriteria = GreatReader(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 30,
            friends = mutableSetOf(),
            readMode = anxiousReader,
            password = "sarasa"
        )

        val user2 = User(
            firstName = "Pablo",
            lastName = "Alvarez",
            username = "pAlvarez",
            email = "pablitoAlvarez@gmail.com",
            birthday = LocalDate.of(1998, 6, 1),
            searchCriteria = GreatReader(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 30,
            friends = mutableSetOf(),
            readMode = fanaticReader,
            password = "sarasa"
        )
        user2.addFavouriteAuthor(miguel)//agrego que es un autor favorito

        val user3 = User(
            firstName = "Martin",
            lastName = "Palermo",
            username = "loco22titan",
            email = "elLoco22Titan@gmail.com",
            birthday = LocalDate.of(1973, 11, 7),
            searchCriteria = GreatReader(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 30,
            friends = mutableSetOf(),
            readMode = recurrentReader,
            password = "sarasa"
        )


        //Creacion de Libros

        val laComunidadDelAnillo = Libro(
            titulo = "Juan Salvador Gabiota",
            autor = tolkien,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.SPANISH),
            fecha = 1,
            imagenURL = "ruta/a/la/imagen"
        )

        val lasDosTorres = Libro(
            titulo = "Las dos torres",
            autor = tolkien,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.SPANISH),
            fecha = 1,
            imagenURL = "ruta/a/la/imagen"
        )

        val elRetornoDelRey = Libro(
            titulo = "El retorno del rey",
            autor = tolkien,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.SPANISH),
            fecha = 1,
            imagenURL = "ruta/a/la/imagen"
        )
        val donQuijote = Libro(
            titulo = "Don Quijote de la mancha",
            autor = miguel,
            paginas = 300,
            palabras = 50000,
            ediciones = 1,
            ventasSemanales = 10001,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.GERMAN, Language.PORTUGUESE, Language.RUSSIAN, Language.ITALIAN, Language.FRENCH),
            fecha = 1,
            imagenURL = "ruta/a/la/imagen"
        )
        val donQuijote2 = Libro(
            titulo = "Don Quijote de la mancha 2 ",
            autor = miguel,
            paginas = 800,
            palabras = 500000,
            ediciones = 1,
            ventasSemanales = 10001,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.GERMAN, Language.PORTUGUESE, Language.RUSSIAN, Language.ITALIAN, Language.FRENCH),
            fecha = 1,
            imagenURL = "ruta/a/la/imagen"
        )


        it("tiene una de edad") {
            val edadEsperada = Period.between(user.birthday(), LocalDate.now()).years
            user.age() shouldBe edadEsperada
        }

        it("Lee un libro No desafiante") {
            val libroNoDesafiante = Libro(
                titulo = "El señor de los anillos",
                autor = tolkien,
                paginas = 11,
                palabras = 300,
                ediciones = 3,
                ventasSemanales = 20000,
                lecturaCompleja = false,
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )

            user.readTimeAvg(libroNoDesafiante) shouldBe 10
        }
        it("Lee un librodesafiante") {
            val libroNoDesafiante = Libro(
                titulo = "El señor de los anillos",
                autor = tolkien,
                paginas = 11,
                palabras = 300,
                ediciones = 3,
                ventasSemanales = 20000,
                lecturaCompleja = true,
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )

            user.readTimeAvg(libroNoDesafiante) shouldBe 20
        }


        it("un lectorPromedio agrega UN libro leido") {

            //Act
            user.addReadBook(elRetornoDelRey)

            //Assert
            user.readBooks().size shouldBe 1
        }

        it("un lectorPromedio leyo un libro"){

            //Act
            user.addReadBook(elRetornoDelRey)

            //Assert
            user.bookIsRead(elRetornoDelRey) shouldBe  true
        }

        it("un lectorPromedio leyo todos los libros de una recomendacion"){

            //Arrange
            user.addReadBook(elRetornoDelRey)
            user.addReadBook(laComunidadDelAnillo)
            val recomendacionConLibrosLeidos = Recomendacion(
                creador = user,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey)
            )

            //Act
            user2.addReadBook(elRetornoDelRey)
            user2.addReadBook(laComunidadDelAnillo)

            //Assert
            user2.recomBooksAreRead(recomendacionConLibrosLeidos) shouldBe true

        }

        it("un lectorPromedio No leyo todos los libros de una recomendacion"){
            user.addReadBook(elRetornoDelRey)
            user.addReadBook(laComunidadDelAnillo)
            user.addReadBook(lasDosTorres)

            //Arrange
            val recomendacionConLibrosNoLeidos = Recomendacion(
                creador = user,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey, lasDosTorres)
            )

            //Act
            user3.addReadBook(elRetornoDelRey)
            user3.addReadBook(laComunidadDelAnillo)

            //Assert
            user3.recomBooksAreRead(recomendacionConLibrosNoLeidos) shouldBe false

        }

        it("un lectorPromedio quiere agregar un libro su lista de libros pendientes"){

            //Act
            user.addBookToRead(lasDosTorres)

            //Assert
            user.booksToRead() shouldBe mutableListOf(lasDosTorres)
        }

        it("Tiempo de lectura de una recomendacion"){

            //Arrange
            val recomendacion = Recomendacion(
                creador = user,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey, lasDosTorres)
            )

            //Assert
            //30 palabras por minuto ...
            // Cant Palabras (40000 / 30) * 3 = 3999.999 --> 4000
            recomendacion.tiempoLecturaRecomendacion(user) shouldBe 4000

        }

        it("Tiempo de lectura ahorrado de una recomendacion"){

            //Arrange
            val recomendacion = Recomendacion(
                creador = user,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey, lasDosTorres)
            )
            //NO RECUERDO COMO SEPARAR LOS IT... POR SEPARADO LOS TEST CORREN
            //PERO TODOS JUNTOS NO PORQUE NO SE INDEPENDIZAN LAS VARIABLES.
            //INSTANCIO OTRO LECTOR PROMEDIO PARA PISARLO. CUANDO SEPAMOS COMO HACERLO LO BORRAMOS
            val lectorPromedio = User(
                firstName = "Marty",
                lastName = "McFly",
                username = "Condensador_De_Flujo",
                email = "volverAlFuturo@gmail.com",
                birthday = LocalDate.of(1968, 6, 9),
                searchCriteria = GreatReader(),
                nativeLanguage = Language.SPANISH,
                readTimeMinAvg = 30,
                friends = mutableSetOf(),
                password = "sarasa"
            )
            //Act
            lectorPromedio.addReadBook(laComunidadDelAnillo)

            //Assert
            //30 palabras por minuto ...
            // Cant Palabras (40000 / 30) = 1333.3333 --> 1333
            recomendacion.tiempoLecturaAhorrado(lectorPromedio) shouldBe 1333.3333333333333

        }

        it("Tiempo de lectura neto de una recomendacion"){

            //Arrange
            val recomendacion = Recomendacion(
                creador = user,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey, lasDosTorres)
            )
            //NO RECUERDO COMO SEPARAR LOS IT... POR SEPARADO LOS TEST CORREN
            //PERO TODOS JUNTOS NO PORQUE NO SE INDEPENDIZAN LAS VARIABLES.
            //INSTANCIO OTRO LECTOR PROMEDIO PARA PISARLO. CUANDO SEPAMOS COMO HACERLO LO BORRAMOS
            val lectorPromedio = User(
                firstName = "Marty",
                lastName = "McFly",
                username = "Condensador_De_Flujo",
                email = "volverAlFuturo@gmail.com",
                birthday = LocalDate.of(1968, 6, 9),
                searchCriteria = GreatReader(),
                nativeLanguage = Language.SPANISH,
                readTimeMinAvg = 30,
                friends = mutableSetOf(),
                password = "sarasa"
            )

            //Act
            lectorPromedio.addReadBook(laComunidadDelAnillo)

            //Assert
            //30 palabras por minuto ...
            // Cant Palabras (40000 / 30) * 2 = 2666.6666 --> lo redondea para a arriba
            recomendacion.tiempoLecturaNeto(lectorPromedio) shouldBe 2666.666666666667

        }
        //-----------------------LectorAnsioso
        it("Usuario1 es un lector Ansioso"){
            user1.readMode() shouldBe anxiousReader
        }

        it("Un lector ansioso reduce el tiempo promedio 20% de un libro no desafiante "){
            val tiempoPromedio=user1.bookReadTime(laComunidadDelAnillo)
            //if (libro.esDesafiante()) (40000 / 30) * 2 else (40000/ 30) es el promedio 1666.6*0.8
            assertEquals(1066.6,tiempoPromedio,0.1)
        }

        it("Un lector ansioso reduce el tiempo promedio 50% de un libro best seller"){
            val tiempoPromedio=user1.bookReadTime(donQuijote)
            //if (libro.esDesafiante()) (40000 / 30) * 2 else (40000/ 30) es el promedio 1333*0.5
            //tiempoPromedio shouldBe 1666.66periodico
            assertEquals(1666.6,tiempoPromedio,0.1)
        }
//-----------------------LectorFanatico
        it("Usuario2 es un lector Fanatico"){
            user2.readMode() shouldBe fanaticReader
        }

        it("Un lector fanatico(Usuario2) tiene un autor preferido que es miguel"){
            user2.isFavouriteAuthor(miguel) shouldBe true
        }

        it("Un lector fanatico(Usuario2) no leyo don quijote"){
            user2.bookIsRead(donQuijote) shouldBe false
        }

        it("Un lector fanatico aumenta el tiempo promedio 2min por pagina si es autor preferido y no lo leyo"){
            val tiempoPromedio=user2.bookReadTime(donQuijote2)
            //if (libro.esDesafiante()) (500000 / 30) * 2 else (500000/ 30) --->  33333.3333  else 16666,66
            // 33333.3333 + 1400 = 34733.3333
            assertEquals(34733.3,tiempoPromedio,0.1)
        }

        it("Un lector fanatico no aumenta el tiempo promedio por pagina porque ya lo leyo"){
            user2.addReadBook(donQuijote)
            val tiempoPromedio=user2.bookReadTime(donQuijote)
            //val tiempoPorPagina = if (libro.paginas() > libro.maxPaginasLibroLargo()) 1 else 2
            // 3332 + 0(
            assertEquals(3333.3,tiempoPromedio,0.1)
        }
//---------------------LectorRecurrente
        it("Usuario3 es un lector Recurrente"){
            user3.readMode() shouldBe recurrentReader
        }

        it("Un lector Recurrente (Usuario3) no leyo ninguna vez"){
            user3.readBooks() shouldNotBeEqual donQuijote
        }

        it("Un lector Recurrente (Usuario3) leyo una vez , 1% menos de velocidad de lectura"){
            user3.addReadBook(donQuijote)
            user3.reReadAmount(donQuijote) shouldBe 1
            val tiempoPromedio=user3.bookReadTime(donQuijote)
            // 3333.333 * 0.99 = 3299,99 (
            assertEquals(3299.9,tiempoPromedio,0.1)
        }

        it("Un lector Recurrente (Usuario3) leyo 2 veces , 1% menos de velocidad de lectura"){
            user3.addReadBook(donQuijote)
            user3.reReadAmount(donQuijote) shouldBe 2
            val tiempoPromedio=user3.bookReadTime(donQuijote)
            // 3299 * 0.99=3266,6666
            assertEquals(3266.6,tiempoPromedio,0.1)
        }

        it("Un lector Recurrente (Usuario3) leyo 10 veces , 5% menos de velocidad de lectura"){
            user3.addReadBook(donQuijote)
            user3.addReadBook(donQuijote)
            user3.addReadBook(donQuijote)
            user3.addReadBook(donQuijote)
            user3.reReadAmount(donQuijote) shouldBe 6
            val tiempoPromedio=user3.bookReadTime(donQuijote)
            // 3333.3333 * 0.95 = 3166,666
            assertEquals(3166.6,tiempoPromedio,0.1)

        }
    }

    describe("Un usuario"){

        val user = User(
            firstName = "Marty",
            lastName = "McFly",
            username = "Condensador_De_Flujo",
            email = "volverAlFuturo@gmail.com",
            birthday = LocalDate.of(1968, 6, 9),
            searchCriteria = GreatReader(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 30,
            friends = mutableSetOf(),
            password = "sarasa"
        )
        val amigo = User(
            firstName = "Juan",
            lastName = "Perez",
            username = "juPerez",
            email = "jperez@gmail.com",
            birthday = LocalDate.of(1988, 1, 12),
            searchCriteria = GreatReader(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 30,
            friends = mutableSetOf(),
            readMode = anxiousReader,
            password = "sarasa"
        )

        it("Agrega un amigo a su lista de amigos"){

            user.addFriend(amigo)

            user.friends() shouldBe mutableSetOf(amigo)
        }
        it("Intenta agregar un amigo que ya tiene en la lista de amigos, lanza excepcion"){

            shouldThrow<Exception> {
                user.addFriend(amigo)
            }
        }

        it("Elimina un amigo de la lista de amigos"){

            user.deleteFriend(amigo)

            amigo.friends() shouldBe mutableSetOf()

        }

        it("Intenta eliminar amigo que no existe en la lista de amigos, lanza excepcion"){

            shouldThrow<Exception> {
                user.deleteFriend(amigo)
            }

        }
    }
})