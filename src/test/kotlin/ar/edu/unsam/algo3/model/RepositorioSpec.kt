
import ar.edu.unsam.algo3.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDate

class RepositorioSpec : DescribeSpec({
    isolationMode = IsolationMode.SingleInstance
    //isolationMode NO se puede usar, porque cada vez que agrego un item al repo
    //si me encuentro en el mismo describe el item permanece en la lista de items copia
    //pero en cada it se restablece un id como null
    //Arrange

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
        paginas = 180,
        palabras = 40_000,
        ediciones = 3,
        ventasSemanales = 10001,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES)
    )

    //USUARIOS

    val user = User(
        firstName = "Marty",
        lastName = "McFly",
        username = "Condensador",
        email = "volverAlFuturo@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf(libro1)
    )

    val user2 = User(
        firstName = "Martin",
        lastName = "Martillo",
        username = "Flujo",
        email = "volverAlFuturo2@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf(libro2)
    )

    val user3 = User(
        firstName = "Felix",
        lastName = "Cat",
        username = "Felix_el_Gato",
        email = "felixCat@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Lenguaje.ESPANIOL,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf(libro3)
    )


    //RECOMENDACIONES

    val recomendacion1 = Recomendacion(
        creador = user,
        resegna = "Estos libros estan buenisimos!",
        libros = mutableSetOf(libro1)
    )

    val recomendacion2 = Recomendacion(
        creador = user2,
        resegna = "Libros de nivel!",
        libros = mutableSetOf(libro2)
    )

    val recomendacion3 = Recomendacion(
        creador = user3,
        resegna = "Magnifico!",
        libros = mutableSetOf(libro3)
    )

    val organizador1 = Particular(cantidadMaxima = 3, porcentajeAdicional = 0.05F)

    val centro1 = CentroLectura(
        listaDeUserConReserva = mutableSetOf(user),
        organizador = organizador1,
        fechas = mutableSetOf(LocalDate.of(2024, 6, 9)),
        libro = libro1
    )

    val centro2 = CentroLectura(
        listaDeUserConReserva = mutableSetOf(user),
        organizador = organizador1,
        fechas = mutableSetOf(LocalDate.of(2024, 6, 9)),
        libro = libro1
    )

    val centro3 = CentroLectura(
        listaDeUserConReserva = mutableSetOf(user),
        organizador = organizador1,
        fechas = mutableSetOf(LocalDate.of(2024, 6, 9)),
        libro = libro1
    )

    repositorioCentroLectura.limpiarParaTest()
    repositorioUsuarios.limpiarParaTest()
    repositorioLibros.limpiarParaTest()
    repositorioAutores.limpiarParaTest()

    describe("REPOSITORIO USUARIO") {

        describe("Dada un repositorio de Usuarios vacio") {

            it("CREA primer usuario") {

                //Act
                repositorioUsuarios.crearItem(user)

                //Assert
                user.id shouldBe 1u
            }

            it("CREA segundo usuario, y obtiene por itemPorId") {

                //Act
                repositorioUsuarios.crearItem(user2)

                //Assert
                repositorioUsuarios.itemPorId(2u) shouldBe user2
            }

            it("CREA un usuario ya existente") {

                shouldThrow<Exception> { repositorioUsuarios.crearItem(user) }
            }
        }

        describe("Dada un repositorio con 2 usuarios") {

            it("BUSCA un usuario por nombre completo") {

                //Assert
                repositorioUsuarios.buscarItems(user.nombreCompleto()) shouldBe mutableListOf(user)
            }

            it("BUSCA usuario por nombre parcial") {
                repositorioUsuarios.buscarItems("art") shouldBe mutableListOf(user, user2)
            }

            it("BUSCA usuario por username") {
                repositorioUsuarios.buscarItems("Flujo") shouldBe mutableListOf(user2)
            }
        }

        describe("ACTUALIZAR usuario") {

            it("ACTUALIZAR usuario en repo") {

                val user2Actualizado = User(
                    firstName = "Martin",
                    lastName = "Martillo",
                    username = "Flujo",
                    email = "volverAlFuturo2@gmail.com",
                    birthday = LocalDate.of(1968, 6, 9),
                    searchCriteria = GreatReader(),
                    nativeLanguage = Lenguaje.ESPANIOL,
                    readTimeMinAvg = 30,
                    friends = mutableSetOf(),
                    readBooks = mutableListOf(libro2)
                )

                user2Actualizado.id = 2u

                repositorioUsuarios.actualizarItem(user2Actualizado)

                repositorioUsuarios.itemPorId(2u) shouldBe user2Actualizado
            }

            it("ACTUALIZAR usuario inexistente") {

                shouldThrow<Exception> { repositorioUsuarios.actualizarItem(user3) }
            }
        }

        describe("ELIMINAR usuarios") {

            it("ELIMINAR un usuario de 2") {

                //act
                repositorioUsuarios.eliminarItem(user)

                //assert
                repositorioUsuarios.buscarItems(user.nombreCompleto()) shouldBe mutableListOf()
            }

            it("ELIMINAR usuario inexistente") {
                shouldThrow<Exception> { repositorioUsuarios.eliminarItem(user) }
            }
        }
    }

    describe("REPOSITORIO AUTOR") {

        describe("Dado un repositorio de Autores vacio") {

            it("CREA primer autor") {

                repositorioAutores.crearItem(autor1)

                autor1.id shouldBe 1u
            }

            it("CREAR un autor ya existente") {

                shouldThrow<Exception> { repositorioAutores.crearItem(autor1) }
            }
        }

        describe("OBTENER POR ID autor") {

            it("OBTENER con id 1") {

                repositorioAutores.itemPorId(1u) shouldBe autor1
            }
        }


        describe("Dada un repositorio con 2 autores") {

            it("BUSCA un autor por nombre parcial") {

                //Assert
                repositorioAutores.buscarItems("1") shouldBe mutableListOf(autor1)
            }

            it("BUSCA usuario por apellido parcial") {
                repositorioAutores.crearItem(autor2)
                repositorioAutores.buscarItems("ape") shouldBe mutableListOf(autor1, autor2)
            }

            it("BUSCA usuario por pseudonimo") {
                repositorioAutores.buscarItems("king") shouldBe mutableListOf(autor1, autor2)
            }
        }

        describe("Dado un repositorio con 2 autores") {

            it("ACTUALIZAR autor en repo") {

                val autor2Actualizado = Autor(
                    nombre = "autor2",
                    apellido = "apellidoAutor2",
                    seudonimo = "king",
                    idiomaNativo = Lenguaje.INGLES,
                    fechaNacimiento = LocalDate.of(1968, 6, 9),
                    premios = mutableListOf()
                )

                autor2Actualizado.id = 2u

                repositorioAutores.actualizarItem(autor2Actualizado)

                repositorioAutores.itemPorId(2u) shouldBe autor2Actualizado
            }

            it("ACTUALIZAR autor inexistente") {

                shouldThrow<Exception> { repositorioAutores.actualizarItem(autor3) }
            }
        }

        describe("Dado un repositorio con 2 autores") {

            it("ELIMINAR un autor") {

                //act
                repositorioAutores.eliminarItem(autor2)

                //assert
                repositorioAutores.itemPorId(1u) shouldBe autor1
            }

            it("ELIMINAR autor inexistente") {
                shouldThrow<Exception> { repositorioAutores.eliminarItem(autor3) }
            }
        }
    }

    describe("REPOSITORIO RECOMENDACIONES") {

        describe("Dado un repositorio de Recomendaciones vacio") {

            it("CREA primera recomendacion") {

                repositorioRecomendaciones.crearItem(recomendacion1)

                recomendacion1.id shouldBe 1u
            }

            it("CREAR una ya existente") {

                shouldThrow<Exception> { repositorioRecomendaciones.crearItem(recomendacion1) }
            }
        }

        describe("OBTENER POR ID recomendacion") {

            it("OBTENER con id") {

                repositorioRecomendaciones.itemPorId(1u) shouldBe recomendacion1
            }
        }

        describe("Dada un repositorio con 2 recomendaciones") {

            repositorioRecomendaciones.crearItem(recomendacion2)

            it("BUSCA un recomendacion por apellido completo de creador") {

                //Assert
                repositorioRecomendaciones.buscarItems("McFly") shouldBe mutableListOf(recomendacion1)
            }

            it("BUSCA recomendacion por titulo parcial") {
                //NO ES CASE SENSITIVE
                repositorioRecomendaciones.buscarItems("Titu") shouldBe mutableListOf(recomendacion1, recomendacion2)
            }

            it("BUSCA recomendacion por reseña") {
                //NO ES CASE SENSITIVE
                repositorioRecomendaciones.buscarItems("buenisimos") shouldBe mutableListOf(recomendacion1)
            }
        }

        describe("Dado un repositorio con 2 recomendaciones") {

            it("ACTUALIZAR recomendacion en repo") {

                val recomendacion1Actualizado = Recomendacion(
                    creador = user,
                    resegna = "Actualizado!",
                    libros = mutableSetOf(libro1)
                )

                recomendacion1Actualizado.id = 1u

                repositorioRecomendaciones.actualizarItem(recomendacion1Actualizado)

                repositorioRecomendaciones.itemPorId(1u) shouldBe recomendacion1Actualizado
            }

            it("ACTUALIZAR recomendacion inexistente") {

                shouldThrow<Exception> { repositorioRecomendaciones.actualizarItem(recomendacion3) }
            }
        }

        describe("Dado un repositorio con 2 recomendaciones") {

            it("ELIMINAR un recomendacion de 2") {

                //act
                repositorioRecomendaciones.eliminarItem(recomendacion1)

                //assert
                repositorioRecomendaciones.itemPorId(1u) shouldBe null
            }

            it("ELIMINAR recomendacion inexistente") {
                shouldThrow<Exception> { repositorioRecomendaciones.eliminarItem(recomendacion3) }
            }
        }
    }

    describe("REPOSITORIO LIBROS") {
        // Arrange
        repositorioLibros.service = stubServiceLibros()

        describe("Dado un repositorio de libros vacio") {

            it("CREA primera libro") {

                repositorioLibros.crearItem(libro1)

                libro1.id shouldBe 1u
            }

            it("CREAR un libro ya existente") {

                shouldThrow<Exception> { repositorioLibros.crearItem(libro1) }
            }
        }

        describe("OBTENER POR ID un libro") {

            it("OBTENER con id") {

                repositorioLibros.itemPorId(1u) shouldBe libro1
            }
        }

        describe("Dada un repositorio con 2 libros") {

            repositorioLibros.crearItem(libro2)

            it("BUSCA un libro por titulo") {

                //Assert
                repositorioLibros.buscarItems("tulo") shouldBe mutableListOf(libro1, libro2)
            }

            it("BUSCA libro por apellido autor") {
                //NO ES CASE SENSITIVE
                repositorioLibros.buscarItems("apellidoAutor1") shouldBe mutableListOf(libro1)
            }
        }

        describe("Dado un repositorio con 2 libros") {

            it("ACTUALIZAR libro en repo") {

                val libro1Actualizado = Libro(
                    titulo = "Titulo1",
                    autor = autor1,
                    paginas = 180,
                    palabras = 40_000,
                    ediciones = 10,
                    ventasSemanales = 10001,
                    lecturaCompleja = false,
                    traducciones = mutableSetOf(Lenguaje.INGLES)
                )

                libro1Actualizado.id = 1u

                repositorioLibros.actualizarItem(libro1Actualizado)

                repositorioLibros.itemPorId(1u) shouldBe libro1Actualizado
            }

            it("ACTUALIZAR recomendacion inexistente") {

                shouldThrow<Exception> { repositorioLibros.actualizarItem(libro3) }
            }
        }

        describe("Dado un repositorio con 2 libros") {

            it("ELIMINAR un recomendacion de 2") {

                //act
                repositorioLibros.eliminarItem(libro1)

                //assert
                repositorioLibros.itemPorId(1u) shouldBe null
            }

            it("ELIMINAR autor inexistente") {
                shouldThrow<Exception> { repositorioLibros.eliminarItem(libro3) }
            }
        }

        describe("SERVICIO DE ACTUALIZACION"){

            //act
            val service = stubServiceLibros()

            it("Libro sin actualizar"){

                libro2.esBestSeller() shouldBe false
            }

            it("Libro actualizado"){

                repositorioLibros.crearItem(libro3)
                repositorioLibros.actualizarLibros()
                libro2.esBestSeller() shouldBe true
            }

            it("Se intenta actualizar libro inexistente"){

                repositorioLibros.eliminarItem(libro3)

                shouldThrow<Exception> { repositorioLibros.actualizarLibros() }
            }
        }
    }

    describe("REPOSITORIO CENTROLECTURA") {

        describe("Dado un repositorio de centros de lecturas vacio") {

            it("CREA primer centro") {

                repositorioCentroLectura.crearItem(centro1)

                centro1.id shouldBe 1u
            }

            it("CREAR uno ya existente") {

                shouldThrow<Exception> { repositorioCentroLectura.crearItem(centro1) }
            }
        }

        describe("OBTENER POR ID centro") {

            it("OBTENER con id") {

                repositorioCentroLectura.itemPorId(1u) shouldBe centro1
            }
        }

        describe("Dada un repositorio con 2 centros") {

            repositorioCentroLectura.crearItem(centro2)

            it("BUSCA un centro por titulo de libro") {

                //Assert
                repositorioCentroLectura.buscarItems("Titulo1") shouldBe mutableListOf(centro1, centro2)
            }

            it("BUSCA centro que no coincida") {
                //NO ES CASE SENSITIVE
                repositorioCentroLectura.buscarItems("zzz") shouldBe mutableListOf()
            }
        }

        describe("Dado un repositorio con 2 centros") {

            it("ACTUALIZAR centro") {

                val centro2Actualizado = CentroLectura(
                    listaDeUserConReserva = mutableSetOf(user),
                    organizador = organizador1,
                    fechas = mutableSetOf(LocalDate.of(2024, 6, 9)),
                    libro = libro1
                )

                centro2Actualizado.id = 2u

                repositorioCentroLectura.actualizarItem(centro2Actualizado)

                repositorioCentroLectura.itemPorId(2u) shouldBe centro2Actualizado
            }

            it("ACTUALIZAR centro inexistente") {

                shouldThrow<Exception> { repositorioCentroLectura.actualizarItem(centro3) }
            }
        }

        describe("Dado un repositorio con 2 centros") {

            it("ELIMINAR un centro") {

                //act
                repositorioCentroLectura.eliminarItem(centro1)

                //assert
                repositorioCentroLectura.itemPorId(1u) shouldBe null
            }

            it("ELIMINAR centro inexistente") {
                shouldThrow<Exception> { repositorioCentroLectura.eliminarItem(centro3) }
            }
        }
    }
})

fun stubServiceLibros(): ServiceLibros{
    @Serializable
    data class LibroPayload(
        val id: UInt,
        val ediciones: Int,
        val ventasSemanales: Int
    )

    val service = mockk<ServiceLibros>(relaxUnitFun = true)

    val json = Json.encodeToString(listOf(LibroPayload(3u, 10, 100001), LibroPayload(2u, 10, 100001)))

    every { service.getLibros() } answers { json }

    return service
}

