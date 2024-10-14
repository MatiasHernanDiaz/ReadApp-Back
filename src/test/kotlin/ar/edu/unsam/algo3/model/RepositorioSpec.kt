
import ar.edu.unsam.algo3.*
import ar.edu.unsam.algo3.repos.RepositorioAutores
import ar.edu.unsam.algo3.repos.RepositorioLibros
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones
import ar.edu.unsam.algo3.repos.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class RepositorioSpec : DescribeSpec({
    isolationMode = IsolationMode.SingleInstance
    //isolationMode NO se puede usar, porque cada vez que agrego un item al repo
    //si me encuentro en el mismo describe el item permanece en la lista de items copia
    //pero en cada it se restablece un id como null
    //Arrange

    //AUTORES

    val autor1 = Author(
        firstName = "autor1",
        lastName = "lastNameAutor1",
        alias = "king",
        nativeLanguage = Language.SPANISH,
        birthday = LocalDate.of(1968, 6, 9),
        prices = mutableListOf()
    )

    val autor2 = Author(
        firstName = "autor2",
        lastName = "lastNameAutor2",
        alias = "king",
        nativeLanguage = Language.ENGLISH,
        birthday = LocalDate.of(1968, 6, 9),
        prices = mutableListOf()
    )

    val autor3 = Author(
        firstName = "autor3",
        lastName = "lastNameAutor3",
        alias = "king",
        nativeLanguage = Language.FRENCH,
        birthday = LocalDate.of(1968, 6, 9),
        prices = mutableListOf()
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
        traducciones = mutableSetOf(Language.ENGLISH),
        fecha = 1,
        imagenURL = "ruta/a/la/imagen"
    )

    val libro2 = Libro(
        titulo = "Titulo2",
        autor = autor2,
        paginas = 180,
        palabras = 40_000,
        ediciones = 2,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Language.ENGLISH),
        fecha = 1,
        imagenURL = "ruta/a/la/imagen"
    )

    val libro3 = Libro(
        titulo = "Titulo3",
        autor = autor3,
        paginas = 180,
        palabras = 40_000,
        ediciones = 3,
        ventasSemanales = 10001,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Language.ENGLISH),
        fecha = 1,
        imagenURL = "ruta/a/la/imagen"
    )

    //USUARIOS

    val user = User(
        firstName = "Marty",
        lastName = "McFly",
        username = "Condensador",
        email = "volverAlFuturo@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf(libro1),
        password = "sarasa"
    )

    val user2 = User(
        firstName = "Martin",
        lastName = "Martillo",
        username = "Flujo",
        email = "volverAlFuturo2@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf(libro2),
        password = "sarasa"
    )

    val user3 = User(
        firstName = "Felix",
        lastName = "Cat",
        username = "Felix_el_Gato",
        email = "felixCat@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf(libro3),
        password = "sarasa"
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

    val repositorioUsuarios = UserRepository()
    val repositorioLibros = RepositorioLibros()
    val repositorioAutores = RepositorioAutores()
    val repositorioRecomendaciones = RepositorioRecomendaciones()


    describe("REPOSITORIO USUARIO") {

        describe("Dada un repositorio de Usuarios vacio") {

            it("CREA primer usuario") {

                //Act
                repositorioUsuarios.createItem(user)

                //Assert
                user.id shouldBe 1
            }

            it("CREA segundo usuario, y obtiene por itemById") {

                //Act
                repositorioUsuarios.createItem(user2)

                //Assert
                repositorioUsuarios.itemById(2) shouldBe user2
            }

            it("CREA un usuario ya existente") {

                shouldThrow<Exception> { repositorioUsuarios.createItem(user) }
            }
        }

        describe("Dada un repositorio con 2 usuarios") {

            it("BUSCA un usuario por firstName completo") {

                //Assert
                repositorioUsuarios.searchItems(user.displayName()) shouldBe mutableListOf(user)
            }

            it("BUSCA usuario por firstName parcial") {
                repositorioUsuarios.searchItems("art") shouldBe mutableListOf(user, user2)
            }

            it("BUSCA usuario por username") {
                repositorioUsuarios.searchItems("Flujo") shouldBe mutableListOf(user2)
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
                    nativeLanguage = Language.SPANISH,
                    readTimeMinAvg = 30,
                    friends = mutableSetOf(),
                    readBooks = mutableListOf(libro2),
                    password = "sarasa"
                )

                user2Actualizado.id = 2

                repositorioUsuarios.updateItem(user2Actualizado)

                repositorioUsuarios.itemById(2) shouldBe user2Actualizado
            }

            it("ACTUALIZAR usuario inexistente") {

                shouldThrow<Exception> { repositorioUsuarios.updateItem(user3) }
            }
        }

        describe("ELIMINAR usuarios") {

            it("ELIMINAR un usuario de 2") {

                //act
                repositorioUsuarios.deleteItem(user)

                //assert
                repositorioUsuarios.searchItems(user.displayName()) shouldBe mutableListOf()
            }

            it("ELIMINAR usuario inexistente") {
                shouldThrow<Exception> { repositorioUsuarios.deleteItem(user) }
            }
        }
    }

    describe("REPOSITORIO AUTOR") {

        describe("Dado un repositorio de Autores vacio") {

            it("CREA primer autor") {

                repositorioAutores.createItem(autor1)

                autor1.id shouldBe 1
            }

            it("CREAR un autor ya existente") {

                shouldThrow<Exception> { repositorioAutores.createItem(autor1) }
            }
        }

        describe("OBTENER POR ID autor") {

            it("OBTENER con id 1") {

                repositorioAutores.itemById(1) shouldBe autor1
            }
        }


        describe("Dada un repositorio con 2 autores") {

            it("BUSCA un autor por firstName parcial") {

                //Assert
                repositorioAutores.searchItems("1") shouldBe mutableListOf(autor1)
            }

            it("BUSCA usuario por lastName parcial") {
                repositorioAutores.createItem(autor2)
                repositorioAutores.searchItems("ape") shouldBe mutableListOf(autor1, autor2)
            }

            it("BUSCA usuario por palias") {
                repositorioAutores.searchItems("king") shouldBe mutableListOf(autor1, autor2)
            }
        }

        describe("Dado un repositorio con 2 autores") {

            it("ACTUALIZAR autor en repo") {

                val autor2Actualizado = Author(
                    firstName = "autor2",
                    lastName = "lastNameAutor2",
                    alias = "king",
                    nativeLanguage = Language.ENGLISH,
                    birthday = LocalDate.of(1968, 6, 9),
                    prices = mutableListOf()
                )

                autor2Actualizado.id = 2

                repositorioAutores.updateItem(autor2Actualizado)

                repositorioAutores.itemById(2) shouldBe autor2Actualizado
            }

            it("ACTUALIZAR autor inexistente") {

                shouldThrow<Exception> { repositorioAutores.updateItem(autor3) }
            }
        }

        describe("Dado un repositorio con 2 autores") {

            it("ELIMINAR un autor") {

                //act
                repositorioAutores.deleteItem(autor2)

                //assert
                repositorioAutores.itemById(1) shouldBe autor1
            }

            it("ELIMINAR autor inexistente") {
                shouldThrow<Exception> { repositorioAutores.deleteItem(autor3) }
            }
        }
    }

    describe("REPOSITORIO RECOMENDACIONES") {

        describe("Dado un repositorio de Recomendaciones vacio") {

            it("CREA primera recomendacion") {

                repositorioRecomendaciones.createItem(recomendacion1)

                recomendacion1.id shouldBe 1
            }

            it("CREAR una ya existente") {

                shouldThrow<Exception> { repositorioRecomendaciones.createItem(recomendacion1) }
            }
        }

        describe("OBTENER POR ID recomendacion") {

            it("OBTENER con id") {

                repositorioRecomendaciones.itemById(1) shouldBe recomendacion1
            }
        }

        describe("Dada un repositorio con 2 recomendaciones") {

            repositorioRecomendaciones.createItem(recomendacion2)

            it("BUSCA un recomendacion por lastName completo de creador") {

                //Assert
                repositorioRecomendaciones.searchItems("McFly") shouldBe mutableListOf(recomendacion1)
            }

            it("BUSCA recomendacion por titulo parcial") {
                //NO ES CASE SENSITIVE
                repositorioRecomendaciones.searchItems("Titu") shouldBe mutableListOf(recomendacion1, recomendacion2)
            }

            it("BUSCA recomendacion por reseña") {
                //NO ES CASE SENSITIVE
                repositorioRecomendaciones.searchItems("buenisimos") shouldBe mutableListOf(recomendacion1)
            }
        }

        describe("Dado un repositorio con 2 recomendaciones") {

            it("ACTUALIZAR recomendacion en repo") {

                val recomendacion1Actualizado = Recomendacion(
                    creador = user,
                    resegna = "Actualizado!",
                    libros = mutableSetOf(libro1)
                )

                recomendacion1Actualizado.id = 1

                repositorioRecomendaciones.updateItem(recomendacion1Actualizado)

                repositorioRecomendaciones.itemById(1) shouldBe recomendacion1Actualizado
            }

            it("ACTUALIZAR recomendacion inexistente") {

                shouldThrow<Exception> { repositorioRecomendaciones.updateItem(recomendacion3) }
            }
        }

        describe("Dado un repositorio con 2 recomendaciones") {

            it("ELIMINAR un recomendacion de 2") {

                //act
                repositorioRecomendaciones.deleteItem(recomendacion1)

                //assert
                repositorioRecomendaciones.itemById(1) shouldBe null
            }

            it("ELIMINAR recomendacion inexistente") {
                shouldThrow<Exception> { repositorioRecomendaciones.deleteItem(recomendacion3) }
            }
        }
    }

    describe("REPOSITORIO LIBROS") {
        // Arrange
        describe("Dado un repositorio de libros vacio") {

            it("CREA primera libro") {

                repositorioLibros.createItem(libro1)

                libro1.id shouldBe 1
            }

            it("CREAR un libro ya existente") {

                shouldThrow<Exception> { repositorioLibros.createItem(libro1) }
            }
        }

        describe("OBTENER POR ID un libro") {

            it("OBTENER con id") {

                repositorioLibros.itemById(1) shouldBe libro1
            }
        }

        describe("Dada un repositorio con 2 libros") {

            repositorioLibros.createItem(libro2)

            it("BUSCA un libro por titulo") {

                //Assert
                repositorioLibros.searchItems("tulo") shouldBe mutableListOf(libro1, libro2)
            }

            it("BUSCA libro por lastName autor") {
                //NO ES CASE SENSITIVE
                repositorioLibros.searchItems("lastNameAutor1") shouldBe mutableListOf(libro1)
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
                    traducciones = mutableSetOf(Language.ENGLISH),
                    fecha = 1,
                    imagenURL = "ruta/a/la/imagen"
                )

                libro1Actualizado.id = 1

                repositorioLibros.updateItem(libro1Actualizado)

                repositorioLibros.itemById(1) shouldBe libro1Actualizado
            }

            it("ACTUALIZAR recomendacion inexistente") {

                shouldThrow<Exception> { repositorioLibros.updateItem(libro3) }
            }
        }

        describe("Dado un repositorio con 2 libros") {

            it("ELIMINAR un recomendacion de 2") {

                //act
                repositorioLibros.deleteItem(libro1)

                //assert
                repositorioLibros.itemById(1) shouldBe null
            }

            it("ELIMINAR autor inexistente") {
                shouldThrow<Exception> { repositorioLibros.deleteItem(libro3) }
            }
        }
    }
})
