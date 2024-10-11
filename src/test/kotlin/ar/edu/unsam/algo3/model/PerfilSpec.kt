package ar.edu.unsam.algo3

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import io.kotest.core.spec.IsolationMode

class PerfilSpec : DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    val premio = Premio()
    val autor = Autor(nombre = "Miguel", apellido = "de Cervantes", seudonimo = "El manco", idiomaNativo = Lenguaje.ESPANIOL,fechaNacimiento = LocalDate.of(1958, 6, 9), premios= mutableListOf())
    val libro = Libro(
        titulo = "Don Quijote de la mancha",
        autor = autor,
        paginas = 300,
        palabras = 50000,
        ediciones = 1,
        ventasSemanales = 6000,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES)
    )
    val autor2 = Autor(nombre = "Miguel", apellido = "de Cervantes", seudonimo = "El manco", idiomaNativo = Lenguaje.INGLES,fechaNacimiento = LocalDate.of(1988, 6, 9), premios= mutableListOf())
    val libro2 = Libro(
        titulo = "Don Quijote de la mancha",
        autor = autor2,
        paginas = 300,
        palabras = 3000,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf(Lenguaje.ESPANIOL)
    )
    val autor3 = Autor(nombre = "Miguel", apellido = "de Cervantes", seudonimo = "El manco", idiomaNativo = Lenguaje.ESPANIOL,fechaNacimiento = LocalDate.of(1988, 6, 9), premios= mutableListOf())
    val libro3 = Libro(
        titulo = "Don Quijote de la mancha",
        autor = autor3,
        paginas = 300,
        palabras = 600,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf( Lenguaje.ITALIANO, Lenguaje.PORTUGUES,Lenguaje.INGLES, Lenguaje.FRANCES)
    )

    val amigo = Usuario(
        nombre = "Marty",
        apellido = "McFly",
        userName = "Condensador_De_Flujo",
        email = "volverAlFuturo@gmail.com",
        fechaNacimiento = LocalDate.of(1938, 6, 9),
        perfil = Leedor(),
        lenguajeNativo = Lenguaje.ESPANIOL,
        palabrasXMinuto = 30,
        amigos = mutableSetOf(),
        librosLeidos = mutableListOf(libro2,libro3)
    )
    val usuario = Usuario(
        nombre = "Marty",
        apellido = "McFly",
        userName = "Condensador_De_Flujo",
        email = "volverAlFuturo@gmail.com",
        fechaNacimiento = LocalDate.of(2008, 6, 9),
        perfil = Leedor(),
        lenguajeNativo = Lenguaje.ESPANIOL,
        palabrasXMinuto = 30,
        amigos = mutableSetOf(amigo)
    )
    val creador = Usuario(
        nombre = "Josuke",
        apellido = "hikashikata",
        userName = "CrazyDiamond",
        email = "Jojo@gmail.com",
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        perfil = Leedor(),
        lenguajeNativo = Lenguaje.ESPANIOL,
        palabrasXMinuto = 30,
        librosLeidos = mutableListOf(libro,libro2,libro3),
        amigos = mutableSetOf()
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

    val calculador = Calculador(
        usuario = usuario,
        tiempoMaximo = 400.0,
        tiempoMinimo = 210.3
    )
    val cambianteUsuario = Cambiante(
        usuario = usuario,
        calculador = calculador
    )

    val cambianteAmigo = Cambiante(
        usuario = amigo,
        calculador = calculador
    )

    val combinado = Combinado(
        usuario = usuario,
        perfiles = mutableSetOf(Poliglota(),Demandante())
    )

    describe("perfil precavido"){
        it("puede leer ya que está en su lista de noLeidos"){
            usuario.modificarPerfil(Precavido(usuario))
            usuario.agregarLibroALeer(libro)
            recomendacion.agregarLibro(creador,libro)

            usuario.esRecomendable(recomendacion) shouldBe true
        }
        it("puede recomendarlo ya que un amigo lo leyó"){
            usuario.modificarPerfil(Precavido(usuario))
            amigo.agregarLibroLeido(libro)
            recomendacion.agregarLibro(creador,libro)

            usuario.esRecomendable(recomendacion) shouldBe true
        }
        it("no lo puede recomendar"){
            usuario.modificarPerfil(Precavido(usuario))
            recomendacion.agregarLibro(creador,libro)

            usuario.esRecomendable(recomendacion) shouldBe false
        }
    }
    describe("perfil leedor"){
        it("puede recomendarlo en cualquier caso"){
            usuario.modificarPerfil(Leedor())
            recomendacion.agregarLibro(creador,libro)

            usuario.esRecomendable(recomendacion) shouldBe true
        }
    }
    describe("perfil poliglota"){
        it("no puede recomendarlo ya que tiene pocos idiomas"){
            usuario.modificarPerfil(Poliglota())
            recomendacion.agregarLibro(creador,libro)
            recomendacion.agregarLibro(creador,libro2)

            usuario.esRecomendable(recomendacion) shouldBe false
        }
        it("es recomendable ya que tiene muchos lenguajes"){
            usuario.modificarPerfil(Poliglota())
            recomendacion.agregarLibro(creador,libro)
            recomendacion.agregarLibro(creador,libro3)

            usuario.esRecomendable(recomendacion) shouldBe true
        }

    }
    describe("perfil nativista"){
        it("puede recomendarlo ya que tanto el autor como el usuario tienen el español como lenguaje nativo"){
            usuario.modificarPerfil(Nativista(usuario))
            recomendacion.agregarLibro(creador,libro)

            usuario.esRecomendable(recomendacion) shouldBe true
        }
        it("no puede recomendarlo ya que no tienen el mismo lenguaje nativo"){
            usuario.modificarPerfil(Nativista(usuario))
            recomendacion.agregarLibro(creador,libro2)

            usuario.esRecomendable(recomendacion) shouldBe false
        }
    }

    describe("perfil calculador"){
        it("es recomendable ya que el tiempo cumple con los requisitos"){
            usuario.modificarPerfil(calculador)
            recomendacion.agregarLibro(creador,libro2)
            recomendacion.agregarLibro(creador,libro3)

            usuario.esRecomendable(recomendacion) shouldBe true
        }
        it("no es recomendable ya que el tiempo sobrepasa los requisitos"){
            usuario.modificarPerfil(calculador)
            recomendacion.agregarLibro(creador,libro)

            usuario.esRecomendable(recomendacion) shouldBe false
        }
        it("no es recomendable ya que el tiempo es demasiado bajo"){
            usuario.modificarPerfil(calculador)
            recomendacion.agregarLibro(creador,libro3)

            usuario.esRecomendable(recomendacion) shouldBe false
        }
    }

    describe("perfil demandante"){
        it("es recomendable ya que tiene de promedio de valoracion un 4"){
            recomendacion.agregarValoracion(valoracion1)
            recomendacion.agregarValoracion(valoracion2)
            usuario.modificarPerfil(Demandante())

            usuario.esRecomendable(recomendacion) shouldBe true
        }
        it("no es recomendable ya que yiene de promedio de valoracion un 2"){
            recomendacion.agregarValoracion(valoracion2)
            recomendacion.agregarValoracion(valoracion3)
            usuario.modificarPerfil(Demandante())

            usuario.esRecomendable(recomendacion) shouldBe false
        }
    }

    describe("perfil experimentado"){
        it("se puede recomendar ya que el autor tiene mas de 50 años"){
            usuario.modificarPerfil(Experimentado())
            recomendacion.agregarLibro(creador,libro)

            usuario.esRecomendable(recomendacion) shouldBe true
        }
        it("se puede recomendar ya que el autor gano un premio"){
            usuario.modificarPerfil(Experimentado())
            autor2.ganarPremio(premio)
            recomendacion.agregarLibro(creador,libro2)

            usuario.esRecomendable(recomendacion) shouldBe true
        }
        it("no se puede recomendar ya que el autor es muy joven y no gano nungun premio"){
            usuario.modificarPerfil(Experimentado())
            recomendacion.agregarLibro(creador,libro3)

            usuario.esRecomendable(recomendacion) shouldBe false
        }
    }

    describe("perfil cambiante"){
        it("al ser joven siempre será recomendable"){
            usuario.modificarPerfil(cambianteUsuario)
            usuario.esRecomendable(recomendacion) shouldBe true
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
            usuario.modificarPerfil(combinado)
            recomendacion.agregarLibro(creador,libro)
            recomendacion.agregarLibro(creador,libro2)

            usuario.esRecomendable(recomendacion) shouldBe false
        }
        it("no puede recomendarlo ya que valoracion baja"){
            usuario.modificarPerfil(combinado)
            recomendacion.agregarLibro(creador,libro3)
            recomendacion.agregarValoracion(valoracion3)

            usuario.esRecomendable(recomendacion) shouldBe false
        }
        it("es recomendable ya que tiene muchos lenguajes y valoracion alta"){
            usuario.modificarPerfil(combinado)
            recomendacion.agregarLibro(creador,libro3)
            recomendacion.agregarValoracion(valoracion1)

            usuario.esRecomendable(recomendacion) shouldBe true
        }
    }
})