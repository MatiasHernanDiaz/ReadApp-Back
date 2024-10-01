package ar.edu.unsam.algo3

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class CentroSpec : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val organizador1 = Particular(cantidadMaxima = 3, porcentajeAdicional = 0.05F)
    val organizador2 = Editorial(autorPresente = true, montoAlcanzar = 10000f)
    val organizador3 = Biblioteca(listaDeGastosFijos = listOf(10000F, 5000F, 15000F), metrosCuadrados = 100)
    val organizador4 = Editorial(autorPresente = false, montoAlcanzar = 10000f)
    val organizador5 = Particular(cantidadMaxima = 40, porcentajeAdicional = 0.05F)
    val miguel = Autor(
        nombre = "Miguel",
        apellido = "de Cervantes",
        seudonimo = "El manco",
        idiomaNativo = Lenguaje.ESPANIOL,
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        premios = mutableListOf()
    )
    val mama = Autor(
        nombre = "Mama",
        apellido = "Fresh",
        seudonimo = "Mamerto",
        idiomaNativo = Lenguaje.ESPANIOL,
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        premios = mutableListOf()
    )
    val guiaCocina = Libro(
        titulo = "aprende a cocinar con Mama Fresh",
        autor = mama,
        paginas = 300,
        palabras = 10000,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = true,
        traducciones = mutableSetOf()
    )
    val donQuijote = Libro(
        titulo = "Don Quijote de la mancha",
        autor = miguel,
        paginas = 300,
        palabras = 50000,
        ediciones = 1,
        ventasSemanales = 15000,
        lecturaCompleja = true,
        traducciones = mutableSetOf(
            Lenguaje.INGLES,
            Lenguaje.ALEMAN,
            Lenguaje.PORTUGUES,
            Lenguaje.RUSO,
            Lenguaje.ITALIANO,
            Lenguaje.FRANCES
        )
    )
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
    val usuario4 = Usuario(
        nombre = "Tony",
        apellido = "Stark",
        userName = "ironMan",
        email = "ironman@gmail.com",
        fechaNacimiento = LocalDate.of(1973, 11, 7),
        perfil = Leedor(),
        lenguajeNativo = Lenguaje.INGLES,
        palabrasXMinuto = 30,
        amigos = mutableSetOf(),
        tipoLector = LectorRecurrente
    )

    val centro1 = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(usuario, usuario1),
        organizador = organizador1,
        fechas = mutableSetOf(LocalDate.of(2025, 6, 9)),
        libro = donQuijote
    )
    val centro1bis = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(),
        organizador = organizador5,
        fechas = mutableSetOf(LocalDate.of(2024, 6, 9)),
        libro = donQuijote
    )

    val centro2 = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(usuario, usuario1),
        organizador = organizador1,
        fechas = mutableSetOf(
            LocalDate.of(2023, 6, 9),
            LocalDate.of(1975, 6, 12),
            LocalDate.of(1975, 6, 15)
        ),
        libro = donQuijote
    )
    val centro3 = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(usuario, usuario1, usuario2),
        organizador = organizador2,
        fechas = mutableSetOf(
            LocalDate.of(2024, 7, 9),
            LocalDate.of(2024, 7, 12),
            LocalDate.of(2024, 7, 15)
        ),
        libro = donQuijote
    )

    val centro4 = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(usuario, usuario1, usuario2),
        organizador = organizador2,
        fechas = mutableSetOf(
            LocalDate.of(2024, 7, 9),
            LocalDate.of(2024, 7, 12),
            LocalDate.of(2024, 7, 15)
        ),
        libro = guiaCocina
    )
    val centro4bis = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(usuario, usuario1, usuario2),
        organizador = organizador4,
        fechas = mutableSetOf(
            LocalDate.of(2024, 7, 9),
            LocalDate.of(2024, 7, 12),
            LocalDate.of(2024, 7, 15)
        ),
        libro = guiaCocina
    )
    val centro5 = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(usuario, usuario1, usuario2, usuario3),
        organizador = organizador3,
        fechas = mutableSetOf(
            LocalDate.of(2024, 7, 9),
            LocalDate.of(2024, 7, 12),
            LocalDate.of(2024, 7, 13),
            LocalDate.of(2024, 7, 15)
        ),
        libro = guiaCocina
    )
    val centro6 = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(usuario, usuario1, usuario2, usuario3),
        organizador = organizador3,
        fechas = mutableSetOf(
            LocalDate.of(2024, 7, 1),
            LocalDate.of(2024, 7, 2),
            LocalDate.of(2024, 7, 3),
            LocalDate.of(2024, 7, 4),
            LocalDate.of(2024, 7, 5),
            LocalDate.of(2024, 7, 6),
            LocalDate.of(2024, 7, 7),
            LocalDate.of(2024, 7, 8),
            LocalDate.of(2024, 7, 9),
            LocalDate.of(2024, 7, 10)

        ),
        libro = guiaCocina
    )

    describe("organizador Particular") {
        it("su costo de reserva es el que dice el organizador particular") {
            centro1.costoReserva() shouldBe 1500f
        }
        it("su cant maxima de participantes es la que dice el organizador") {
            centro1.cantMaxParicipantes() shouldBe 3
        }
        it("el primer centro no esta expirado ya que no tiene demasiados usuarios y una de las fechas todavia no paso") {
            centro1.expiroPlublicacion() shouldBe false
        }
        it("el segundo centro esta expirado ya que, aunque no tiene demasiados usuarios, todas sus fechas ya pasaron") {
            centro2.expiroPlublicacion() shouldBe true
        }
        it("el primer centro expiro ya que tiene demasiados usuarios incluso aunque sus fechas no ocurrieron aun") {
            centro1.reservarUsuario(usuario2)
            centro1.expiroPlublicacion() shouldBe true
        }
        it("tira excepcion al mandar demasiados usuarios") {
            centro1.reservarUsuario(usuario2)
            // centro.reservarUsuario(Usuario(nombre = "Marty", apellido = "McFly", userName = "Condensador_De_Flujo", email = "volverAlFuturo@gmail.com", fechaNacimiento = LocalDate.of(1968, 6, 9), perfil = Leedor(), lenguajeNativo = Lenguaje.ESPANIOL, palabrasXMinuto = 30, amigos = mutableSetOf()))
            shouldThrow<Exception> { centro1.reservarUsuario(usuario2) }
        }

        it("agrego a un centro particular y evaluo lo por costo al agregar"){
            centro1bis.reservarUsuario(usuario)
            centro1bis.costoReserva() shouldBe 1000f
            centro1bis.reservarUsuario(usuario1)
            centro1bis.costoReserva() shouldBe 1000f
            centro1bis.reservarUsuario(usuario2)
            centro1bis.costoReserva() shouldBe 1500f
            centro1bis.reservarUsuario(usuario3)
            centro1bis.costoReserva() shouldBe 1500f
        }

    }
    describe("organizador Editorial") {

        it("La cantidad maxima de participantes es segun la Editorial") {
            centro3.cantMaxParicipantes() shouldBe 5  //costo base 1800 monto a alcanzar 10000 = 10
        }

        it("su costo de reserva es segun la Editorial si es bestSeller") {
            centro3.costoReserva() shouldBe 3300F //1000 base+800  +15000*0.1=3300
        }

        it("su costo de reserva es segun la Editorial si no es bestSeller") {
            centro4.costoReserva() shouldBe 2000F //1000 base+800  +200 = 3.300
        }

        it ("En un centro Editorial si el autor no esta presente"){
            centro4bis.reservarUsuario(usuario4)
            centro4bis.costoReserva() shouldBe 1800F //1000 base+800  +200 = 3.300
        }
    }

    describe("organizador Biblioteca") {

        it("La cantidad maxima de participantes es segun la Biblioteca") {
            centro5.cantMaxParicipantes() shouldBe 100  //1metro cuadrado = 1 espacio
        }

        it("su costo de reserva si fueron 4 dias en Biblioteca ") {
            centro5.costoReserva() shouldBe 10500F // 11250 = ( 30000 + 30000 * 40 %) / 4(presentes).
        }
        it("su costo de reserva si fueron 10 dias en Biblioteca ") {
            centro6.costoReserva() shouldBe 11250F //$ 450 = (30000 + 30000 * 50 %) / 4(presentes).
        }



    }
})