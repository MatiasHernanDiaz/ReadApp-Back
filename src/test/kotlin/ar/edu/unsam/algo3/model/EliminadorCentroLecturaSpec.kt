import ar.edu.unsam.algo3.*
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import java.time.LocalDate

class EliminadorCentroLecturaSpec : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val autor1 = Autor(
        nombre = "pablo",
        apellido = "fernandez",
        seudonimo = "king",
        idiomaNativo = Lenguaje.ESPANIOL,
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        premios = mutableListOf()
    )

    val libro1 = Libro(
        titulo = "don quijote",
        autor = autor1,
        imageURL = "",
        paginas = 180,
        palabras = 40_000,
        ediciones = 1,
        ventasSemanales = 10001,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES)
    )

    val centroExpirado = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(),
        organizador = Particular(cantidadMaxima = 3, porcentajeAdicional = 0.05F),
        fechas = mutableSetOf(LocalDate.of(2023, 7, 1)), // Fecha expiro
        libro = libro1
    )
    val centroUnaFechaExpirada = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(),
        organizador = Particular(cantidadMaxima = 3, porcentajeAdicional = 0.05F),
        fechas = mutableSetOf(
            LocalDate.of(2023, 7, 1),
            LocalDate.now().plusDays(10)
        ), // Fecha expiro
        libro = libro1
    )

    val centroVigente = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(),
        organizador = Particular(cantidadMaxima = 3, porcentajeAdicional = 0.05F),
        fechas = mutableSetOf(LocalDate.now().plusDays(10)), // Fecha que no expira
        libro = libro1
    )
    val centroVigente2 = CentroLectura(
        listaDeUsuarioConReserva = mutableSetOf(),
        organizador = Particular(cantidadMaxima = 3, porcentajeAdicional = 0.05F),
        fechas = mutableSetOf(LocalDate.now().plusDays(10)), // Fecha que no expira
        libro = libro1
    )

    repositorioCentroLectura.limpiarParaTest()
    repositorioCentroLectura.apply {
        crearItem(centroExpirado)
        crearItem(centroVigente)
        crearItem(centroVigente2)
        crearItem(centroUnaFechaExpirada)
    }

    val eliminaCentros = EliminadorCentroLectura()

    val mockedEnviaMail = mockk<EnviaMail>(relaxUnitFun = true)

    eliminaCentros.enviadorMail = mockedEnviaMail

    it("Despues de Ejecutar el Limpiador de centros con fechas expiradas") {
        repositorioCentroLectura.ejecutarAccion(eliminaCentros)
        repositorioCentroLectura.items().size shouldBe 3
    }
})
