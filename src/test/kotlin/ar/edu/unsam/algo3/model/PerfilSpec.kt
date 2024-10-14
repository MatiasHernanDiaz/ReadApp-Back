package ar.edu.unsam.algo3

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import io.kotest.core.spec.IsolationMode

class PerfilSpec : DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    val premio = Premio()
    val autor = Author(firstName = "Miguel", lastName = "de Cervantes", alias = "El manco", nativeLanguage = Language.SPANISH,birthday = LocalDate.of(1958, 6, 9), prices= mutableListOf())
    val libro = Libro(
        titulo = "Don Quijote de la mancha",
        autor = autor,
        paginas = 300,
        palabras = 50000,
        ediciones = 1,
        ventasSemanales = 6000,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Language.ENGLISH, Language.FRENCH),
        fecha = 1,
        imagenURL = "ruta/a/la/imagen"
    )
    val autor2 = Author(firstName = "Miguel", lastName = "de Cervantes", alias = "El manco", nativeLanguage = Language.ENGLISH,birthday = LocalDate.of(1988, 6, 9), prices= mutableListOf())
    val libro2 = Libro(
        titulo = "Don Quijote de la mancha",
        autor = autor2,
        paginas = 300,
        palabras = 3000,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Language.SPANISH),
        fecha = 1,
        imagenURL = "ruta/a/la/imagen"
    )
    val autor3 = Author(firstName = "Miguel", lastName = "de Cervantes", alias = "El manco", nativeLanguage = Language.SPANISH,birthday = LocalDate.of(1988, 6, 9), prices= mutableListOf())
    val libro3 = Libro(
        titulo = "Don Quijote de la mancha",
        autor = autor3,
        paginas = 300,
        palabras = 600,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf( Language.ITALIAN, Language.PORTUGUESE,Language.ENGLISH, Language.FRENCH),
        fecha = 1,
        imagenURL = "ruta/a/la/imagen"
    )

    val amigo = User(
        firstName = "Marty",
        lastName = "McFly",
        username = "Condensador_De_Flujo",
        email = "volverAlFuturo@gmail.com",
        birthday = LocalDate.of(1938, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 30,
        friends = mutableSetOf(),
        readBooks = mutableListOf(libro2,libro3),
        password = "sarasa"
    )
    val user = User(
        firstName = "Marty",
        lastName = "McFly",
        username = "Condensador_De_Flujo",
        email = "volverAlFuturo@gmail.com",
        birthday = LocalDate.of(2008, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 30,
        friends = mutableSetOf(amigo),
        password = "sarasa"
    )
    val creador = User(
        firstName = "Josuke",
        lastName = "hikashikata",
        username = "CrazyDiamond",
        email = "Jojo@gmail.com",
        birthday = LocalDate.of(1968, 6, 9),
        searchCriteria = GreatReader(),
        nativeLanguage = Language.SPANISH,
        readTimeMinAvg = 30,
        readBooks = mutableListOf(libro,libro2,libro3),
        friends = mutableSetOf(),
        password = "sarasa"
    )
    val recomendacion = Recomendacion(
        creador = creador,
        resegna = "Estos libros están buenísimos!!",
        libros = mutableSetOf()
    )

    val valoracion1 = Valoracion(
        rating = 5,
        description = "la mejor valoracion del mundo",
        autor = amigo
    )
    val valoracion2 = Valoracion(
        rating = 3,
        description = "masomenos",
        autor = amigo
        )
    val valoracion3 = Valoracion(
        rating = 1,
        description = "nada que ver lo que opinás",
        autor = amigo
    )

    val calculator = Calculator(
        user = user,
        tiempoMaximo = 400.0,
        tiempoMinimo = 210.3
    )
    val cambianteUsuario = Inconstant(
        user = user,
        calculator = calculator
    )

    val cambianteAmigo = Inconstant(
        user = amigo,
        calculator = calculator
    )

    val combined = Combined(
        user = user,
        perfiles = mutableSetOf(Polyglot(),Claimant())
    )

    describe("perfil precavido"){
        it("puede leer ya que está en su lista de noLeidos"){
            user.updateSearchCriteria(Cautious(user))
            user.addBookToRead(libro)
            recomendacion.agregarLibro(creador,libro)

            user.isRecommendable(recomendacion) shouldBe true
        }
        it("puede recomendarlo ya que un amigo lo leyó"){
            user.updateSearchCriteria(Cautious(user))
            amigo.addReadBook(libro)
            recomendacion.agregarLibro(creador,libro)

            user.isRecommendable(recomendacion) shouldBe true
        }
        it("no lo puede recomendar"){
            user.updateSearchCriteria(Cautious(user))
            recomendacion.agregarLibro(creador,libro)

            user.isRecommendable(recomendacion) shouldBe false
        }
    }
    describe("perfil leedor"){
        it("puede recomendarlo en cualquier caso"){
            user.updateSearchCriteria(GreatReader())
            recomendacion.agregarLibro(creador,libro)

            user.isRecommendable(recomendacion) shouldBe true
        }
    }
    describe("perfil poliglota"){
        it("no puede recomendarlo ya que tiene pocos idiomas"){
            user.updateSearchCriteria(Polyglot())
            recomendacion.agregarLibro(creador,libro)
            recomendacion.agregarLibro(creador,libro2)

            user.isRecommendable(recomendacion) shouldBe false
        }
        it("es recomendable ya que tiene muchos lenguajes"){
            user.updateSearchCriteria(Polyglot())
            recomendacion.agregarLibro(creador,libro)
            recomendacion.agregarLibro(creador,libro3)

            user.isRecommendable(recomendacion) shouldBe true
        }

    }
    describe("perfil nativista"){
        it("puede recomendarlo ya que tanto el autor como el usuario tienen el español como lenguaje nativo"){
            user.updateSearchCriteria(Nativist(user))
            recomendacion.agregarLibro(creador,libro)

            user.isRecommendable(recomendacion) shouldBe true
        }
        it("no puede recomendarlo ya que no tienen el mismo lenguaje nativo"){
            user.updateSearchCriteria(Nativist(user))
            recomendacion.agregarLibro(creador,libro2)

            user.isRecommendable(recomendacion) shouldBe false
        }
    }

    describe("perfil calculador"){
        it("es recomendable ya que el tiempo cumple con los requisitos"){
            user.updateSearchCriteria(calculator)
            recomendacion.agregarLibro(creador,libro2)
            recomendacion.agregarLibro(creador,libro3)

            user.isRecommendable(recomendacion) shouldBe true
        }
        it("no es recomendable ya que el tiempo sobrepasa los requisitos"){
            user.updateSearchCriteria(calculator)
            recomendacion.agregarLibro(creador,libro)

            user.isRecommendable(recomendacion) shouldBe false
        }
        it("no es recomendable ya que el tiempo es demasiado bajo"){
            user.updateSearchCriteria(calculator)
            recomendacion.agregarLibro(creador,libro3)

            user.isRecommendable(recomendacion) shouldBe false
        }
    }

    describe("perfil demandante"){
        it("es recomendable ya que tiene de promedio de valoracion un 4"){
            recomendacion.agregarValoracion(valoracion1)
            recomendacion.agregarValoracion(valoracion2)
            user.updateSearchCriteria(Claimant())

            user.isRecommendable(recomendacion) shouldBe true
        }
        it("no es recomendable ya que yiene de promedio de valoracion un 2"){
            recomendacion.agregarValoracion(valoracion2)
            recomendacion.agregarValoracion(valoracion3)
            user.updateSearchCriteria(Claimant())

            user.isRecommendable(recomendacion) shouldBe false
        }
    }

    describe("perfil experimentado"){
        it("se puede recomendar ya que el autor tiene mas de 50 años"){
            user.updateSearchCriteria(Experiencied())
            recomendacion.agregarLibro(creador,libro)

            user.isRecommendable(recomendacion) shouldBe true
        }
        it("se puede recomendar ya que el autor gano un premio"){
            user.updateSearchCriteria(Experiencied())
            autor2.winPrice(premio)
            recomendacion.agregarLibro(creador,libro2)

            user.isRecommendable(recomendacion) shouldBe true
        }
        it("no se puede recomendar ya que el autor es muy joven y no gano nungun premio"){
            user.updateSearchCriteria(Experiencied())
            recomendacion.agregarLibro(creador,libro3)

            user.isRecommendable(recomendacion) shouldBe false
        }
    }

    describe("perfil cambiante"){
        it("al ser joven siempre será recomendable"){
            user.updateSearchCriteria(cambianteUsuario)
            user.isRecommendable(recomendacion) shouldBe true
        }
        it("como es mas adulto, será recomendable ya que está en su limite de tiempo"){
            amigo.updateSearchCriteria(cambianteAmigo)
            recomendacion.agregarLibro(creador,libro2)
            recomendacion.agregarLibro(creador,libro3)

            amigo.isRecommendable(recomendacion) shouldBe true
        }
        it("como tiene mayor edad, no sera recomendable ya que el tiempo es demasiado alto"){
            amigo.updateSearchCriteria(cambianteAmigo)
            recomendacion.agregarLibro(creador,libro)

            amigo.isRecommendable(recomendacion) shouldBe false
        }
        it("como tiene mayor edad, no sera recomendable ya que el tiempo es demasiado bajo"){
            amigo.updateSearchCriteria(cambianteAmigo)
            recomendacion.agregarLibro(creador,libro3)

            amigo.isRecommendable(recomendacion) shouldBe false
        }
    }

    describe("perfil combinado"){
        it("no puede recomendarlo ya que tiene pocos idiomas"){
            user.updateSearchCriteria(combined)
            recomendacion.agregarLibro(creador,libro)
            recomendacion.agregarLibro(creador,libro2)

            user.isRecommendable(recomendacion) shouldBe false
        }
        it("no puede recomendarlo ya que valoracion baja"){
            user.updateSearchCriteria(combined)
            recomendacion.agregarLibro(creador,libro3)
            recomendacion.agregarValoracion(valoracion3)

            user.isRecommendable(recomendacion) shouldBe false
        }
        it("es recomendable ya que tiene muchos lenguajes y valoracion alta"){
            user.updateSearchCriteria(combined)
            recomendacion.agregarLibro(creador,libro3)
            recomendacion.agregarValoracion(valoracion1)

            user.isRecommendable(recomendacion) shouldBe true
        }
    }
})