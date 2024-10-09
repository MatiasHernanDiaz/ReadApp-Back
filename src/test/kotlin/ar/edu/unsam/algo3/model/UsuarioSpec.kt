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

        val miguel = Autor(nombre = "Miguel", apellido = "de Cervantes", seudonimo = "El manco", idiomaNativo = Lenguaje.ESPANIOL,fechaNacimiento = LocalDate.of(1978, 6, 1), premios= mutableListOf())
        val tolkien = Autor(nombre = "J. R. R.", apellido = "Tolkien", seudonimo = "Hobbit", idiomaNativo = Lenguaje.INGLES,fechaNacimiento = LocalDate.of(1978, 6, 1), premios= mutableListOf())

        //Arrange
        //Creacion de Lectores

        val user = User(
            firstName = "Marty",
            lastName = "McFly",
            username = "Condensador_De_Flujo",
            email = "volverAlFuturo@gmail.com",
            birthday = LocalDate.of(1968, 6, 9),
            searchCriteria = GreatReader(),
            nativeLanguage = Lenguaje.ESPANIOL,
            readTimeMinAvg = 30,
            friends = mutableSetOf()
        )
        val user1 = User(
            firstName = "Juan",
            lastName = "Perez",
            username = "juPerez",
            email = "jperez@gmail.com",
            birthday = LocalDate.of(1988, 1, 12),
            searchCriteria = GreatReader(),
            nativeLanguage = Lenguaje.ESPANIOL,
            readTimeMinAvg = 30,
            friends = mutableSetOf(),
            readMode = LectorAnsioso
        )

        val user2 = User(
            firstName = "Pablo",
            lastName = "Alvarez",
            username = "pAlvarez",
            email = "pablitoAlvarez@gmail.com",
            birthday = LocalDate.of(1998, 6, 1),
            searchCriteria = GreatReader(),
            nativeLanguage = Lenguaje.ESPANIOL,
            readTimeMinAvg = 30,
            friends = mutableSetOf(),
            readMode = LectorFanatico
        )
        user2.agregarAutorPreferido(miguel)//agrego que es un autor favorito

        val user3 = User(
            firstName = "Martin",
            lastName = "Palermo",
            username = "loco22titan",
            email = "elLoco22Titan@gmail.com",
            birthday = LocalDate.of(1973, 11, 7),
            searchCriteria = GreatReader(),
            nativeLanguage = Lenguaje.ESPANIOL,
            readTimeMinAvg = 30,
            friends = mutableSetOf(),
            readMode = LectorRecurrente
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
            traducciones = mutableSetOf(Lenguaje.ESPANIOL),
        )

        val lasDosTorres = Libro(
            titulo = "Las dos torres",
            autor = tolkien,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL),
        )

        val elRetornoDelRey = Libro(
            titulo = "El retorno del rey",
            autor = tolkien,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL),
        )
        val donQuijote = Libro(
            titulo = "Don Quijote de la mancha",
            autor = miguel,
            paginas = 300,
            palabras = 50000,
            ediciones = 1,
            ventasSemanales = 10001,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ALEMAN, Lenguaje.PORTUGUES, Lenguaje.RUSO, Lenguaje.ITALIANO, Lenguaje.FRANCES)
        )
        val donQuijote2 = Libro(
            titulo = "Don Quijote de la mancha 2 ",
            autor = miguel,
            paginas = 800,
            palabras = 500000,
            ediciones = 1,
            ventasSemanales = 10001,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ALEMAN, Lenguaje.PORTUGUES, Lenguaje.RUSO, Lenguaje.ITALIANO, Lenguaje.FRANCES)
        )


        it("tiene una de edad") {
            val edadEsperada = Period.between(user.fechaNacimiento(), LocalDate.now()).years
            user.edad() shouldBe edadEsperada
        }

        it("Lee un libro No desafiante") {
            val libroNoDesafiante = Libro(
                titulo = "El señor de los anillos",
                autor = tolkien,
                paginas = 11,
                palabras = 300,
                ediciones = 3,
                ventasSemanales = 20000,
                lecturaCompleja = false
            )

            user.tiempoLecturaPromedio(libroNoDesafiante) shouldBe 10
        }
        it("Lee un librodesafiante") {
            val libroNoDesafiante = Libro(
                titulo = "El señor de los anillos",
                autor = tolkien,
                paginas = 11,
                palabras = 300,
                ediciones = 3,
                ventasSemanales = 20000,
                lecturaCompleja = true
            )

            user.tiempoLecturaPromedio(libroNoDesafiante) shouldBe 20
        }


        it("un lectorPromedio agrega UN libro leido") {

            //Act
            user.agregarLibroLeido(elRetornoDelRey)

            //Assert
            user.listaLibrosLeidos().size shouldBe 1
        }

        it("un lectorPromedio leyo un libro"){

            //Act
            user.agregarLibroLeido(elRetornoDelRey)

            //Assert
            user.libroLeido(elRetornoDelRey) shouldBe  true
        }

        it("un lectorPromedio leyo todos los libros de una recomendacion"){

            //Arrange
            user.agregarLibroLeido(elRetornoDelRey)
            user.agregarLibroLeido(laComunidadDelAnillo)
            val recomendacionConLibrosLeidos = Recomendacion(
                creador = user,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey)
            )

            //Act
            user2.agregarLibroLeido(elRetornoDelRey)
            user2.agregarLibroLeido(laComunidadDelAnillo)

            //Assert
            user2.todosLosLibrosLeidos(recomendacionConLibrosLeidos) shouldBe true

        }

        it("un lectorPromedio No leyo todos los libros de una recomendacion"){
            user.agregarLibroLeido(elRetornoDelRey)
            user.agregarLibroLeido(laComunidadDelAnillo)
            user.agregarLibroLeido(lasDosTorres)

            //Arrange
            val recomendacionConLibrosNoLeidos = Recomendacion(
                creador = user,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey, lasDosTorres)
            )

            //Act
            user3.agregarLibroLeido(elRetornoDelRey)
            user3.agregarLibroLeido(laComunidadDelAnillo)

            //Assert
            user3.todosLosLibrosLeidos(recomendacionConLibrosNoLeidos) shouldBe false

        }

        it("un lectorPromedio quiere agregar un libro su lista de libros pendientes"){

            //Act
            user.agregarLibroALeer(lasDosTorres)

            //Assert
            user.listaDeLibrosALeer() shouldBe mutableListOf(lasDosTorres)
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
                nativeLanguage = Lenguaje.ESPANIOL,
                readTimeMinAvg = 30,
                friends = mutableSetOf()
            )
            //Act
            lectorPromedio.agregarLibroLeido(laComunidadDelAnillo)

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
                nativeLanguage = Lenguaje.ESPANIOL,
                readTimeMinAvg = 30,
                friends = mutableSetOf()
            )

            //Act
            lectorPromedio.agregarLibroLeido(laComunidadDelAnillo)

            //Assert
            //30 palabras por minuto ...
            // Cant Palabras (40000 / 30) * 2 = 2666.6666 --> lo redondea para a arriba
            recomendacion.tiempoLecturaNeto(lectorPromedio) shouldBe 2666.666666666667

        }
        //-----------------------LectorAnsioso
        it("Usuario1 es un lector Ansioso"){
            user1.tipoDeLector() shouldBe LectorAnsioso
        }

        it("Un lector ansioso reduce el tiempo promedio 20% de un libro no desafiante "){
            val tiempoPromedio=user1.tiempoLecturaLibro(laComunidadDelAnillo)
            //if (libro.esDesafiante()) (40000 / 30) * 2 else (40000/ 30) es el promedio 1666.6*0.8
            assertEquals(1066.6,tiempoPromedio,0.1)
        }

        it("Un lector ansioso reduce el tiempo promedio 50% de un libro best seller"){
            val tiempoPromedio=user1.tiempoLecturaLibro(donQuijote)
            //if (libro.esDesafiante()) (40000 / 30) * 2 else (40000/ 30) es el promedio 1333*0.5
            //tiempoPromedio shouldBe 1666.66periodico
            assertEquals(1666.6,tiempoPromedio,0.1)
        }
//-----------------------LectorFanatico
        it("Usuario2 es un lector Fanatico"){
            user2.tipoDeLector() shouldBe LectorFanatico
        }

        it("Un lector fanatico(Usuario2) tiene un autor preferido que es miguel"){
            user2.esAutorPreferido(miguel) shouldBe true
        }

        it("Un lector fanatico(Usuario2) no leyo don quijote"){
            user2.libroLeido(donQuijote) shouldBe false
        }

        it("Un lector fanatico aumenta el tiempo promedio 2min por pagina si es autor preferido y no lo leyo"){
            val tiempoPromedio=user2.tiempoLecturaLibro(donQuijote2)
            //if (libro.esDesafiante()) (500000 / 30) * 2 else (500000/ 30) --->  33333.3333  else 16666,66
            // 33333.3333 + 1400 = 34733.3333
            assertEquals(34733.3,tiempoPromedio,0.1)
        }

        it("Un lector fanatico no aumenta el tiempo promedio por pagina porque ya lo leyo"){
            user2.agregarLibroLeido(donQuijote)
            val tiempoPromedio=user2.tiempoLecturaLibro(donQuijote)
            //val tiempoPorPagina = if (libro.paginas() > libro.maxPaginasLibroLargo()) 1 else 2
            // 3332 + 0(
            assertEquals(3333.3,tiempoPromedio,0.1)
        }
//---------------------LectorRecurrente
        it("Usuario3 es un lector Recurrente"){
            user3.tipoDeLector() shouldBe LectorRecurrente
        }

        it("Un lector Recurrente (Usuario3) no leyo ninguna vez"){
            user3.listaLibrosLeidos() shouldNotBeEqual donQuijote
        }

        it("Un lector Recurrente (Usuario3) leyo una vez , 1% menos de velocidad de lectura"){
            user3.agregarLibroLeido(donQuijote)
            user3.cantidadDeReleida(donQuijote) shouldBe 1
            val tiempoPromedio=user3.tiempoLecturaLibro(donQuijote)
            // 3333.333 * 0.99 = 3299,99 (
            assertEquals(3299.9,tiempoPromedio,0.1)
        }

        it("Un lector Recurrente (Usuario3) leyo 2 veces , 1% menos de velocidad de lectura"){
            user3.agregarLibroLeido(donQuijote)
            user3.cantidadDeReleida(donQuijote) shouldBe 2
            val tiempoPromedio=user3.tiempoLecturaLibro(donQuijote)
            // 3299 * 0.99=3266,6666
            assertEquals(3266.6,tiempoPromedio,0.1)
        }

        it("Un lector Recurrente (Usuario3) leyo 10 veces , 5% menos de velocidad de lectura"){
            user3.agregarLibroLeido(donQuijote)
            user3.agregarLibroLeido(donQuijote)
            user3.agregarLibroLeido(donQuijote)
            user3.agregarLibroLeido(donQuijote)
            user3.cantidadDeReleida(donQuijote) shouldBe 6
            val tiempoPromedio=user3.tiempoLecturaLibro(donQuijote)
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
            nativeLanguage = Lenguaje.ESPANIOL,
            readTimeMinAvg = 30,
            friends = mutableSetOf()
        )
        val amigo = User(
            firstName = "Juan",
            lastName = "Perez",
            username = "juPerez",
            email = "jperez@gmail.com",
            birthday = LocalDate.of(1988, 1, 12),
            searchCriteria = GreatReader(),
            nativeLanguage = Lenguaje.ESPANIOL,
            readTimeMinAvg = 30,
            friends = mutableSetOf(),
            readMode = LectorAnsioso
        )

        it("Agrega un amigo a su lista de amigos"){

            user.agregarAmigo(amigo)

            user.amigos() shouldBe mutableSetOf(amigo)
        }
        it("Intenta agregar un amigo que ya tiene en la lista de amigos, lanza excepcion"){

            shouldThrow<Exception> {
                user.agregarAmigo(amigo)
            }
        }

        it("Elimina un amigo de la lista de amigos"){

            user.eliminarAmigo(amigo)

            amigo.amigos() shouldBe mutableSetOf()

        }

        it("Intenta eliminar amigo que no existe en la lista de amigos, lanza excepcion"){

            shouldThrow<Exception> {
                user.eliminarAmigo(amigo)
            }

        }
    }
})