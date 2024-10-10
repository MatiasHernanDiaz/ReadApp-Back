package ar.edu.unsam.algo3

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import io.kotest.core.spec.IsolationMode

class PerfilSpec : DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    val premio = Premio()
    val autor = Autor(nombre = "Miguel", apellido = "de Cervantes", seudonimo = "El manco", idiomaNativo = Language.SPANISH,fechaNacimiento = LocalDate.of(1958, 6, 9), premios= mutableListOf())
    val libro = Libro(
        titulo = "Don Quijote de la mancha",
        autor = autor,
        paginas = 300,
        palabras = 50000,
        ediciones = 1,
        ventasSemanales = 6000,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Language.ENGLISH, Language.FRENCH)
    )
    val autor2 = Autor(nombre = "Miguel", apellido = "de Cervantes", seudonimo = "El manco", idiomaNativo = Language.ENGLISH,fechaNacimiento = LocalDate.of(1988, 6, 9), premios= mutableListOf())
    val libro2 = Libro(
        titulo = "Don Quijote de la mancha",
        autor = autor2,
        paginas = 300,
        palabras = 3000,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Language.SPANISH)
    )
    val autor3 = Autor(nombre = "Miguel", apellido = "de Cervantes", seudonimo = "El manco", idiomaNativo = Language.SPANISH,fechaNacimiento = LocalDate.of(1988, 6, 9), premios= mutableListOf())
    val libro3 = Libro(
        titulo = "Don Quijote de la mancha",
        autor = autor3,
        paginas = 300,
        palabras = 600,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf( Language.ITALIAN, Language.PORTUGUESE,Language.ENGLISH, Language.FRENCH)
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
        puntuacion = 5,
        comentario = "la mejor valoracion del mundo",
        autor = amigo
    )
    val valoracion2 = Valoracion(
        puntuacion = 3,
        comentario = "masomenos",
        autor = amigo
        )
    val valoracion3 = Valoracion(
        puntuacion = 1,
        comentario = "nada que ver lo que opinás",
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
            user.modificarPerfil(Cautious(user))
            user.agregarLibroALeer(libro)
            recomendacion.agregarLibro(creador,libro)

            user.esRecomendable(recomendacion) shouldBe true
        }
        it("puede recomendarlo ya que un amigo lo leyó"){
            user.modificarPerfil(Cautious(user))
            amigo.agregarLibroLeido(libro)
            recomendacion.agregarLibro(creador,libro)

            user.esRecomendable(recomendacion) shouldBe true
        }
        it("no lo puede recomendar"){
            user.modificarPerfil(Cautious(user))
            recomendacion.agregarLibro(creador,libro)

            user.esRecomendable(recomendacion) shouldBe false
        }
    }
    describe("perfil leedor"){
        it("puede recomendarlo en cualquier caso"){
            user.modificarPerfil(GreatReader())
            recomendacion.agregarLibro(creador,libro)

            user.esRecomendable(recomendacion) shouldBe true
        }
    }
    describe("perfil poliglota"){
        it("no puede recomendarlo ya que tiene pocos idiomas"){
            user.modificarPerfil(Polyglot())
            recomendacion.agregarLibro(creador,libro)
            recomendacion.agregarLibro(creador,libro2)

            user.esRecomendable(recomendacion) shouldBe false
        }
        it("es recomendable ya que tiene muchos lenguajes"){
            user.modificarPerfil(Polyglot())
            recomendacion.agregarLibro(creador,libro)
            recomendacion.agregarLibro(creador,libro3)

            user.esRecomendable(recomendacion) shouldBe true
        }

    }
    describe("perfil nativista"){
        it("puede recomendarlo ya que tanto el autor como el usuario tienen el español como lenguaje nativo"){
            user.modificarPerfil(Nativist(user))
            recomendacion.agregarLibro(creador,libro)

            user.esRecomendable(recomendacion) shouldBe true
        }
        it("no puede recomendarlo ya que no tienen el mismo lenguaje nativo"){
            user.modificarPerfil(Nativist(user))
            recomendacion.agregarLibro(creador,libro2)

            user.esRecomendable(recomendacion) shouldBe false
        }
    }

    describe("perfil calculador"){
        it("es recomendable ya que el tiempo cumple con los requisitos"){
            user.modificarPerfil(calculator)
            recomendacion.agregarLibro(creador,libro2)
            recomendacion.agregarLibro(creador,libro3)

            user.esRecomendable(recomendacion) shouldBe true
        }
        it("no es recomendable ya que el tiempo sobrepasa los requisitos"){
            user.modificarPerfil(calculator)
            recomendacion.agregarLibro(creador,libro)

            user.esRecomendable(recomendacion) shouldBe false
        }
        it("no es recomendable ya que el tiempo es demasiado bajo"){
            user.modificarPerfil(calculator)
            recomendacion.agregarLibro(creador,libro3)

            user.esRecomendable(recomendacion) shouldBe false
        }
    }

    describe("perfil demandante"){
        it("es recomendable ya que tiene de promedio de valoracion un 4"){
            recomendacion.agregarValoracion(valoracion1)
            recomendacion.agregarValoracion(valoracion2)
            user.modificarPerfil(Claimant())

            user.esRecomendable(recomendacion) shouldBe true
        }
        it("no es recomendable ya que yiene de promedio de valoracion un 2"){
            recomendacion.agregarValoracion(valoracion2)
            recomendacion.agregarValoracion(valoracion3)
            user.modificarPerfil(Claimant())

            user.esRecomendable(recomendacion) shouldBe false
        }
    }

    describe("perfil experimentado"){
        it("se puede recomendar ya que el autor tiene mas de 50 años"){
            user.modificarPerfil(Experiencied())
            recomendacion.agregarLibro(creador,libro)

            user.esRecomendable(recomendacion) shouldBe true
        }
        it("se puede recomendar ya que el autor gano un premio"){
            user.modificarPerfil(Experiencied())
            autor2.ganarPremio(premio)
            recomendacion.agregarLibro(creador,libro2)

            user.esRecomendable(recomendacion) shouldBe true
        }
        it("no se puede recomendar ya que el autor es muy joven y no gano nungun premio"){
            user.modificarPerfil(Experiencied())
            recomendacion.agregarLibro(creador,libro3)

            user.esRecomendable(recomendacion) shouldBe false
        }
    }

    describe("perfil cambiante"){
        it("al ser joven siempre será recomendable"){
            user.modificarPerfil(cambianteUsuario)
            user.esRecomendable(recomendacion) shouldBe true
        }
        it("como es mas adulto, será recomendable ya que está en su limite de tiempo"){
            amigo.modificarPerfil(cambianteAmigo)
            recomendacion.agregarLibro(creador,libro2)
            recomendacion.agregarLibro(creador,libro3)

            amigo.esRecomendable(recomendacion) shouldBe true
        }
        it("como tiene mayor edad, no sera recomendable ya que el tiempo es demasiado alto"){
            amigo.modificarPerfil(cambianteAmigo)
            recomendacion.agregarLibro(creador,libro)

            amigo.esRecomendable(recomendacion) shouldBe false
        }
        it("como tiene mayor edad, no sera recomendable ya que el tiempo es demasiado bajo"){
            amigo.modificarPerfil(cambianteAmigo)
            recomendacion.agregarLibro(creador,libro3)

            amigo.esRecomendable(recomendacion) shouldBe false
        }
    }

    describe("perfil combinado"){
        it("no puede recomendarlo ya que tiene pocos idiomas"){
            user.modificarPerfil(combined)
            recomendacion.agregarLibro(creador,libro)
            recomendacion.agregarLibro(creador,libro2)

            user.esRecomendable(recomendacion) shouldBe false
        }
        it("no puede recomendarlo ya que valoracion baja"){
            user.modificarPerfil(combined)
            recomendacion.agregarLibro(creador,libro3)
            recomendacion.agregarValoracion(valoracion3)

            user.esRecomendable(recomendacion) shouldBe false
        }
        it("es recomendable ya que tiene muchos lenguajes y valoracion alta"){
            user.modificarPerfil(combined)
            recomendacion.agregarLibro(creador,libro3)
            recomendacion.agregarValoracion(valoracion1)

            user.esRecomendable(recomendacion) shouldBe true
        }
    }
})