import ar.edu.unsam.algo3.*
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldBe
import java.time.LocalDate


class RecomendacionSpec : DescribeSpec({
    // isolationMode = IsolationMode.InstancePerLeaf
    
    // Arrange
    val amigoDelCreador = User(
        firstName = "Juan Luis",
        lastName = "Guerra",
        username = "pez",
        email = "pez@gmail.com",
        birthday = LocalDate.of(1988, 8, 15),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 300,
        friends = mutableSetOf(),
        password = "sarasa"
    )

    val otroAmigoDelCreador = User(
        firstName = "Rubén",
        lastName = "Rada",
        username = "negro",
        email = "negrorada@gmail.com",
        birthday = LocalDate.of(1958, 2, 15),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 300,
        friends = mutableSetOf(),
        password = "sarasa"
    )

    val userCualquiera = User(
        firstName = "Leonor",
        lastName = "Benedetto",
        username = "leona",
        email = "leo@gmail.com",
        birthday = LocalDate.of(1978, 8, 25),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 300,
        friends = mutableSetOf(),
        password = "sarasa"
    )
    val creadorRecom = User(
        firstName = "Pedro",
        lastName = "Picapiedras",
        username = "piedra",
        email = "piedra@gmail.com",
        birthday = LocalDate.of(1990, 8, 24),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 300,
        friends = mutableSetOf(amigoDelCreador, otroAmigoDelCreador),
        password = "sarasa"
    )

    val autorPreferido = Autor(
        nombre = "Jorge Luis",
        apellido = "Borges",
        seudonimo = "cieguito",
        idiomaNativo = Language.SPANISH,
        fechaNacimiento = LocalDate.of(1978, 6, 1)
    )
    val otroAutorPreferido = Autor(
        nombre = "Julio",
        apellido = "Cortázar",
        seudonimo = "Julito",
        idiomaNativo = Language.ENGLISH,
        fechaNacimiento = LocalDate.of(1978, 6, 1)
    )
    val autorNoPreferido = Autor(
        nombre = "Bernardo",
        apellido = "Stamateas",
        seudonimo = "berni",
        idiomaNativo = Language.SPANISH,
        fechaNacimiento = LocalDate.of(1978, 6, 1)
    )

    val libroAutorPreferido = Libro(
        titulo = "Aleph",
        autor = autorPreferido,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Language.ENGLISH),
    )

    val libroOtroAutorPreferido = Libro(
        titulo = "Rayuela",
        autor = otroAutorPreferido,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Language.ENGLISH),
    )

    val libroAutorNoPreferido = Libro(
        titulo = "Sarasa",
        autor = autorNoPreferido,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Language.ENGLISH)
    )

    val valoracion = Valoracion(puntuacion = 5, comentario = "Buenisimo Bro!", autor = userCualquiera)

    creadorRecom.agregarLibroLeido(libroAutorPreferido)
    creadorRecom.agregarLibroLeido(libroOtroAutorPreferido)
    creadorRecom.agregarLibroLeido(libroAutorNoPreferido)

    val recomCompleta = Recomendacion(
        creador = creadorRecom,
        resegna = "Estos libros están buenísimos!",
        libros = mutableSetOf(libroAutorPreferido, libroOtroAutorPreferido, libroAutorNoPreferido)
    )

    describe("Tests sobre recomendaciones") {
        describe("Pruebas sobre la inicialización de una recomendación") {
            it("si el creador de la recomendación no leyó todos los libros de la recomendación, lanza una excepción") {
                shouldThrow<Exception> {
                    Recomendacion(
                        creador = userCualquiera,
                        resegna = "Estos libros están buenísimos!",
                        libros = mutableSetOf(libroAutorPreferido, libroOtroAutorPreferido, libroAutorNoPreferido)
                    )
                }
            }
            it("si el creador lee los libro de la recomendación, la misma puede instanciarse") {
                // Act
                userCualquiera.agregarLibroLeido(libroAutorPreferido)
                userCualquiera.agregarLibroLeido(libroOtroAutorPreferido)
                userCualquiera.agregarLibroLeido(libroAutorNoPreferido)
                // Assert
                shouldNotThrow<Exception> {
                    Recomendacion(
                        creador = userCualquiera,
                        resegna = "Estos libros están buenísimos!",
                        libros = mutableSetOf(libroAutorPreferido, libroOtroAutorPreferido, libroAutorNoPreferido)
                    )
                }
            }
        }

        describe("Métodos sobre la privacidad de la recomendación") {
            it("si el creador cambia la privacidad por defecto, la recomendación pasa a ser pública") {
                // Act
                recomCompleta.cambiarPrivacidad(creadorRecom)
                // Assert
                recomCompleta.esPublica() shouldBe true
            }
            it("si un usuario cualquiera cambia la privacidad, lanza una excepción") {
                // Assert
                shouldThrow<Exception> { recomCompleta.cambiarPrivacidad(userCualquiera) }
            }
            it("si vuelve a editar un amigo que leyó todos los libros, la recomendación vuelve a ser privada") {
                // Act
                amigoDelCreador.agregarLibroLeido(libroAutorPreferido)
                amigoDelCreador.agregarLibroLeido(libroOtroAutorPreferido)
                amigoDelCreador.agregarLibroLeido(libroAutorNoPreferido)
                recomCompleta.cambiarPrivacidad(amigoDelCreador)

                recomCompleta.esPublica() shouldBe false
            }
            it("si vuelve a editar un amigo que no leyó todos los libros, lanza una excpeción") {
                // Act
                otroAmigoDelCreador.agregarLibroLeido(libroAutorPreferido)
                shouldThrow<Exception> { recomCompleta.cambiarPrivacidad(otroAmigoDelCreador) }
            }
        }

        describe("Métodos para editar la resegna") {
            it("si el creador edita la reseña, debe ser distinta de la anterior") {
                // Arrange
                val viejaResegna = recomCompleta.resegna()
                // Act
                recomCompleta.editarResegna(creadorRecom, "Estos libros son bastante malos.")
                // Assert
                recomCompleta.resegna() shouldNotBeEqual viejaResegna
            }
            it("si un amigo que no leyó todos los libros edita la reseña, lanza una excepción.") {

                shouldThrow<Exception> { recomCompleta.editarResegna(otroAmigoDelCreador, "No concuerdo") }
            }
        }

        describe("Métodos para editar los libros") {
            // Arrange
            val nuevoLibro = Libro(
                titulo = "Un libro nuevo",
                autor = Autor(nombre = "Rolando", apellido = "Hanglin", seudonimo = "rolo", idiomaNativo = Language.SPANISH, fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf()),
                paginas = 200,
                palabras = 10_000,
                ediciones = 1,
                ventasSemanales = 100,
                lecturaCompleja = false,
                traducciones = mutableSetOf(),
            )

            val otroNuevoLibro = Libro(
                titulo = "Otro libro nuevo",
                autor = Autor(nombre = "Beto", apellido = "Cascella", seudonimo = "beto", idiomaNativo = Language.SPANISH, fechaNacimiento = LocalDate.of(1978, 6, 1)),
                paginas = 200,
                palabras = 10_000,
                ediciones = 1,
                ventasSemanales = 100,
                lecturaCompleja = false,
                traducciones = mutableSetOf(),
            )

            it("si el creador agrega un libro que no leyó, la cantidad de libros totales debe seguir siendo 3") {
                // Assert
                shouldThrow<Exception> { recomCompleta.agregarLibro(creadorRecom, nuevoLibro) }
            }
            it("si el creador agrega un libro que leyó, la cantidad de libros totales debe ser 4") {
                // Act
                creadorRecom.agregarLibroLeido(nuevoLibro)
                recomCompleta.agregarLibro(creadorRecom, nuevoLibro)
                // Assert
                recomCompleta.libros().count() shouldBe  4
            }
            it("si un editor no autorizado intenta eliminar un libro, lanza una excepción, aunque haya leido el libro") {
                // Act
                userCualquiera.agregarLibroLeido(nuevoLibro)
                // Assert
                shouldThrow<Exception> { recomCompleta.eliminarLibro(userCualquiera, nuevoLibro) }
            }
            it("si un amigo que leyó todos los libros intenta agregar un libro que no leyó, lanza una excepción") {
                // Act
                amigoDelCreador.agregarLibroLeido(nuevoLibro)
                // Assert
                shouldThrow<Exception> { recomCompleta.agregarLibro(amigoDelCreador, otroNuevoLibro) }
            }
            it("si un amigo que leyó todos los libros intenta agregar un libro que él leyó pero no leyó el creador, lanza una excepción") {
                // Act
                amigoDelCreador.agregarLibroLeido(otroNuevoLibro)
                // Assert
                shouldThrow<Exception> { recomCompleta.agregarLibro(amigoDelCreador, otroNuevoLibro) }
            }
            it("si un amigo que leyó todos los libros intenta agregar un libro que leyó él y el creador, la cantidad de libros totales debe ser 5") {
                // Act
                creadorRecom.agregarLibroLeido(otroNuevoLibro)
                recomCompleta.agregarLibro(amigoDelCreador, otroNuevoLibro)
                // Assert
                recomCompleta.libros().count() shouldBe  5
            }
            it("si un amigo que no leyó todos los libros intenta eliminar un libro, lanza una excepción") {
                // Assert
                shouldThrow<Exception> { recomCompleta.eliminarLibro(otroAmigoDelCreador, nuevoLibro) }
            }
            it("si un amigo que leyó todos los libros intenta eliminar un libro, la cantidad de libros totales debe ser 4") {
                // Act
                recomCompleta.eliminarLibro(amigoDelCreador, nuevoLibro)
                // Assert
                recomCompleta.libros().count() shouldBe 4
            }
        }

        describe("Métodos sobre las valoraciones") {
            it("si el creador valora la reseña, lanza una excepción") {
                // Assert
                shouldThrow<Exception> { creadorRecom.valorarRecomendacion(recomCompleta, 4, "Tá buenís!") }
            }
            it("si un amigo que no leyó todos los libros valora la reseña, lanza una excepción") {
                // Assert
                shouldThrow<Exception> { otroAmigoDelCreador.valorarRecomendacion(recomCompleta, 4, "Tá buenís!") }
            }
            it("si un amigo que leyó todos los libros valora la reseña, la cantidad de valoraciones es 1.") {
                // Act
                amigoDelCreador.valorarRecomendacion(recomCompleta, 4, "Tá buenís!")
                // Assert
                recomCompleta.valoraciones().count() shouldBe 1
            }
            it("si un amigo que no leyó todos los libros, pero todos los libros son de su autor favorito, valora una nueva reseña, la cantidad de valoraciones es 1.") {
                // Arrange
                val otroLibroDeAutorPreferido = Libro(
                    titulo = "Fervor de Buenos Aires",
                    autor = autorPreferido,
                    paginas = 180,
                    palabras = 40_000,
                    ediciones = 4,
                    ventasSemanales = 120,
                    lecturaCompleja = false,
                    traducciones = mutableSetOf(Language.ENGLISH),
                )
                creadorRecom.agregarLibroLeido(otroLibroDeAutorPreferido)
                val recomDeAutorPreferido = Recomendacion(
                    creador = creadorRecom,
                    resegna = "Estos libros están buenísimos!",
                    libros = mutableSetOf(libroAutorPreferido, otroLibroDeAutorPreferido)
                )
                // Act
                otroAmigoDelCreador.agregarAutorPreferido(autorPreferido)
                otroAmigoDelCreador.valorarRecomendacion(recomDeAutorPreferido, 4, "Tá muy bué!")
                // Assert
                recomDeAutorPreferido.valoraciones().count() shouldBe 1
            }
            it("si sumo una valoración de 3 puntos, el promedio debe dar 3.5") {
                // Act
                amigoDelCreador.valorarRecomendacion(
                    recomCompleta, 3, "Muy bué!"
                )
                recomCompleta.promedioValoraciones() shouldBe 3.5
            }
            it("si sumo otra valoración de 3 puntos, el promedio debe dar 3.33") {
                // Act
                amigoDelCreador.valorarRecomendacion(
                    recomCompleta, 3, "Re bué!"
                )
                recomCompleta.promedioValoraciones() shouldBe 3.3333333333333335
            }
            it("El autor de la valoracion puede editar comentario"){
                valoracion.editarComentario(userCualquiera, "Buenisimo KPO")
                valoracion.comentario shouldBe "Buenisimo KPO"
            }

            it("El autor de la valoracion puede editar puntuacion"){
                valoracion.editarPuntuacion(userCualquiera, 3)
                valoracion.puntuacion shouldBe 3
            }

            it("Un usuario que no es el autor de la valoracion no puede editar puntuacion"){
                shouldThrow<Exception> {
                    valoracion.editarPuntuacion(amigoDelCreador, 3)
                }
            }

            it("Un usuario que no es el autor de la valoracion no puede editar comentario"){
                shouldThrow<Exception> {
                    valoracion.editarComentario(amigoDelCreador, "3 porque es un asco")
                }
            }

        }
    }
})