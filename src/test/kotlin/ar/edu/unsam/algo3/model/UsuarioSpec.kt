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

        val usuario = Usuario(
            nombre = "Marty",
            apellido = "McFly",
            userName = "Condensador_De_Flujo",
            email = "volverAlFuturo@gmail.com",
            fechaNacimiento = LocalDate.of(1968, 6, 9),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 30,
            amigos = mutableSetOf()
        )
        val usuario1 = Usuario(
            nombre = "Juan",
            apellido = "Perez",
            userName = "juPerez",
            email = "jperez@gmail.com",
            fechaNacimiento = LocalDate.of(1988, 1, 12),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 30,
            amigos = mutableSetOf(),
            tipoLector = LectorAnsioso
        )

        val usuario2 = Usuario(
            nombre = "Pablo",
            apellido = "Alvarez",
            userName = "pAlvarez",
            email = "pablitoAlvarez@gmail.com",
            fechaNacimiento = LocalDate.of(1998, 6, 1),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 30,
            amigos = mutableSetOf(),
            tipoLector = LectorFanatico
        )
        usuario2.agregarAutorPreferido(miguel)//agrego que es un autor favorito

        val usuario3 = Usuario(
            nombre = "Martin",
            apellido = "Palermo",
            userName = "loco22titan",
            email = "elLoco22Titan@gmail.com",
            fechaNacimiento = LocalDate.of(1973, 11, 7),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 30,
            amigos = mutableSetOf(),
            tipoLector = LectorRecurrente
        )


        //Creacion de Libros

        val laComunidadDelAnillo = Libro(
            titulo = "Juan Salvador Gabiota",
            autor = tolkien,
            imageURL = "",
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
            imageURL = "",
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
            imageURL = "",
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
            imageURL = "",
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
            imageURL = "",
            paginas = 800,
            palabras = 500000,
            ediciones = 1,
            ventasSemanales = 10001,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ALEMAN, Lenguaje.PORTUGUES, Lenguaje.RUSO, Lenguaje.ITALIANO, Lenguaje.FRANCES)
        )


        it("tiene una de edad") {
            val edadEsperada = Period.between(usuario.fechaNacimiento(), LocalDate.now()).years
            usuario.edad() shouldBe edadEsperada
        }

        it("Lee un libro No desafiante") {
            val libroNoDesafiante = Libro(
                titulo = "El señor de los anillos",
                autor = tolkien,
                imageURL = "",
                paginas = 11,
                palabras = 300,
                ediciones = 3,
                ventasSemanales = 20000,
                lecturaCompleja = false
            )

            usuario.tiempoLecturaPromedio(libroNoDesafiante) shouldBe 10
        }
        it("Lee un librodesafiante") {
            val libroNoDesafiante = Libro(
                titulo = "El señor de los anillos",
                autor = tolkien,
                imageURL = "",
                paginas = 11,
                palabras = 300,
                ediciones = 3,
                ventasSemanales = 20000,
                lecturaCompleja = true
            )

            usuario.tiempoLecturaPromedio(libroNoDesafiante) shouldBe 20
        }


        it("un lectorPromedio agrega UN libro leido") {

            //Act
            usuario.agregarLibroLeido(elRetornoDelRey)

            //Assert
            usuario.listaLibrosLeidos().size shouldBe 1
        }

        it("un lectorPromedio leyo un libro"){

            //Act
            usuario.agregarLibroLeido(elRetornoDelRey)

            //Assert
            usuario.libroLeido(elRetornoDelRey) shouldBe  true
        }

        it("un lectorPromedio leyo todos los libros de una recomendacion"){

            //Arrange
            usuario.agregarLibroLeido(elRetornoDelRey)
            usuario.agregarLibroLeido(laComunidadDelAnillo)
            val recomendacionConLibrosLeidos = Recomendacion(
                creador = usuario,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey)
            )

            //Act
            usuario2.agregarLibroLeido(elRetornoDelRey)
            usuario2.agregarLibroLeido(laComunidadDelAnillo)

            //Assert
            usuario2.todosLosLibrosLeidos(recomendacionConLibrosLeidos) shouldBe true

        }

        it("un lectorPromedio No leyo todos los libros de una recomendacion"){
            usuario.agregarLibroLeido(elRetornoDelRey)
            usuario.agregarLibroLeido(laComunidadDelAnillo)
            usuario.agregarLibroLeido(lasDosTorres)

            //Arrange
            val recomendacionConLibrosNoLeidos = Recomendacion(
                creador = usuario,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey, lasDosTorres)
            )

            //Act
            usuario3.agregarLibroLeido(elRetornoDelRey)
            usuario3.agregarLibroLeido(laComunidadDelAnillo)

            //Assert
            usuario3.todosLosLibrosLeidos(recomendacionConLibrosNoLeidos) shouldBe false

        }

        it("un lectorPromedio quiere agregar un libro su lista de libros pendientes"){

            //Act
            usuario.agregarLibroALeer(lasDosTorres)

            //Assert
            usuario.listaDeLibrosALeer() shouldBe mutableListOf(lasDosTorres)
        }

        it("Tiempo de lectura de una recomendacion"){

            //Arrange
            val recomendacion = Recomendacion(
                creador = usuario,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey, lasDosTorres)
            )

            //Assert
            //30 palabras por minuto ...
            // Cant Palabras (40000 / 30) * 3 = 3999.999 --> 4000
            recomendacion.tiempoLecturaRecomendacion(usuario) shouldBe 4000

        }

        it("Tiempo de lectura ahorrado de una recomendacion"){

            //Arrange
            val recomendacion = Recomendacion(
                creador = usuario,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey, lasDosTorres)
            )
            //NO RECUERDO COMO SEPARAR LOS IT... POR SEPARADO LOS TEST CORREN
            //PERO TODOS JUNTOS NO PORQUE NO SE INDEPENDIZAN LAS VARIABLES.
            //INSTANCIO OTRO LECTOR PROMEDIO PARA PISARLO. CUANDO SEPAMOS COMO HACERLO LO BORRAMOS
            val lectorPromedio = Usuario(
                nombre = "Marty",
                apellido = "McFly",
                userName = "Condensador_De_Flujo",
                email = "volverAlFuturo@gmail.com",
                fechaNacimiento = LocalDate.of(1968, 6, 9),
                perfil = Leedor(),
                lenguajeNativo = Lenguaje.ESPANIOL,
                palabrasXMinuto = 30,
                amigos = mutableSetOf()
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
                creador = usuario,
                resegna = "Estos libros están buenísimos!!",
                libros = mutableSetOf(laComunidadDelAnillo ,elRetornoDelRey, lasDosTorres)
            )
            //NO RECUERDO COMO SEPARAR LOS IT... POR SEPARADO LOS TEST CORREN
            //PERO TODOS JUNTOS NO PORQUE NO SE INDEPENDIZAN LAS VARIABLES.
            //INSTANCIO OTRO LECTOR PROMEDIO PARA PISARLO. CUANDO SEPAMOS COMO HACERLO LO BORRAMOS
            val lectorPromedio = Usuario(
                nombre = "Marty",
                apellido = "McFly",
                userName = "Condensador_De_Flujo",
                email = "volverAlFuturo@gmail.com",
                fechaNacimiento = LocalDate.of(1968, 6, 9),
                perfil = Leedor(),
                lenguajeNativo = Lenguaje.ESPANIOL,
                palabrasXMinuto = 30,
                amigos = mutableSetOf()
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
            usuario1.tipoDeLector() shouldBe LectorAnsioso
        }

        it("Un lector ansioso reduce el tiempo promedio 20% de un libro no desafiante "){
            val tiempoPromedio=usuario1.tiempoLecturaLibro(laComunidadDelAnillo)
            //if (libro.esDesafiante()) (40000 / 30) * 2 else (40000/ 30) es el promedio 1666.6*0.8
            assertEquals(1066.6,tiempoPromedio,0.1)
        }

        it("Un lector ansioso reduce el tiempo promedio 50% de un libro best seller"){
            val tiempoPromedio=usuario1.tiempoLecturaLibro(donQuijote)
            //if (libro.esDesafiante()) (40000 / 30) * 2 else (40000/ 30) es el promedio 1333*0.5
            //tiempoPromedio shouldBe 1666.66periodico
            assertEquals(1666.6,tiempoPromedio,0.1)
        }
//-----------------------LectorFanatico
        it("Usuario2 es un lector Fanatico"){
            usuario2.tipoDeLector() shouldBe LectorFanatico
        }

        it("Un lector fanatico(Usuario2) tiene un autor preferido que es miguel"){
            usuario2.esAutorPreferido(miguel) shouldBe true
        }

        it("Un lector fanatico(Usuario2) no leyo don quijote"){
            usuario2.libroLeido(donQuijote) shouldBe false
        }

        it("Un lector fanatico aumenta el tiempo promedio 2min por pagina si es autor preferido y no lo leyo"){
            val tiempoPromedio=usuario2.tiempoLecturaLibro(donQuijote2)
            //if (libro.esDesafiante()) (500000 / 30) * 2 else (500000/ 30) --->  33333.3333  else 16666,66
            // 33333.3333 + 1400 = 34733.3333
            assertEquals(34733.3,tiempoPromedio,0.1)
        }

        it("Un lector fanatico no aumenta el tiempo promedio por pagina porque ya lo leyo"){
            usuario2.agregarLibroLeido(donQuijote)
            val tiempoPromedio=usuario2.tiempoLecturaLibro(donQuijote)
            //val tiempoPorPagina = if (libro.paginas() > libro.maxPaginasLibroLargo()) 1 else 2
            // 3332 + 0(
            assertEquals(3333.3,tiempoPromedio,0.1)
        }
//---------------------LectorRecurrente
        it("Usuario3 es un lector Recurrente"){
            usuario3.tipoDeLector() shouldBe LectorRecurrente
        }

        it("Un lector Recurrente (Usuario3) no leyo ninguna vez"){
            usuario3.listaLibrosLeidos() shouldNotBeEqual donQuijote
        }

        it("Un lector Recurrente (Usuario3) leyo una vez , 1% menos de velocidad de lectura"){
            usuario3.agregarLibroLeido(donQuijote)
            usuario3.cantidadDeReleida(donQuijote) shouldBe 1
            val tiempoPromedio=usuario3.tiempoLecturaLibro(donQuijote)
            // 3333.333 * 0.99 = 3299,99 (
            assertEquals(3299.9,tiempoPromedio,0.1)
        }

        it("Un lector Recurrente (Usuario3) leyo 2 veces , 1% menos de velocidad de lectura"){
            usuario3.agregarLibroLeido(donQuijote)
            usuario3.cantidadDeReleida(donQuijote) shouldBe 2
            val tiempoPromedio=usuario3.tiempoLecturaLibro(donQuijote)
            // 3299 * 0.99=3266,6666
            assertEquals(3266.6,tiempoPromedio,0.1)
        }

        it("Un lector Recurrente (Usuario3) leyo 10 veces , 5% menos de velocidad de lectura"){
            usuario3.agregarLibroLeido(donQuijote)
            usuario3.agregarLibroLeido(donQuijote)
            usuario3.agregarLibroLeido(donQuijote)
            usuario3.agregarLibroLeido(donQuijote)
            usuario3.cantidadDeReleida(donQuijote) shouldBe 6
            val tiempoPromedio=usuario3.tiempoLecturaLibro(donQuijote)
            // 3333.3333 * 0.95 = 3166,666
            assertEquals(3166.6,tiempoPromedio,0.1)

        }
    }

    describe("Un usuario"){

        val usuario = Usuario(
            nombre = "Marty",
            apellido = "McFly",
            userName = "Condensador_De_Flujo",
            email = "volverAlFuturo@gmail.com",
            fechaNacimiento = LocalDate.of(1968, 6, 9),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 30,
            amigos = mutableSetOf()
        )
        val amigo = Usuario(
            nombre = "Juan",
            apellido = "Perez",
            userName = "juPerez",
            email = "jperez@gmail.com",
            fechaNacimiento = LocalDate.of(1988, 1, 12),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 30,
            amigos = mutableSetOf(),
            tipoLector = LectorAnsioso
        )

        it("Agrega un amigo a su lista de amigos"){

            usuario.agregarAmigo(amigo)

            usuario.amigos() shouldBe mutableSetOf(amigo)
        }
        it("Intenta agregar un amigo que ya tiene en la lista de amigos, lanza excepcion"){

            shouldThrow<Exception> {
                usuario.agregarAmigo(amigo)
            }
        }

        it("Elimina un amigo de la lista de amigos"){

            usuario.eliminarAmigo(amigo)

            amigo.amigos() shouldBe mutableSetOf()

        }

        it("Intenta eliminar amigo que no existe en la lista de amigos, lanza excepcion"){

            shouldThrow<Exception> {
                usuario.eliminarAmigo(amigo)
            }

        }
    }
})