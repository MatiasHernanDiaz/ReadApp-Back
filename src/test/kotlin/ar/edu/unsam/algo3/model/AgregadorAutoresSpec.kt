import ar.edu.unsam.algo3.*
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDate

class AgregadorAutoresSpec : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val cervantes = Autor(
        nombre = "Miguel",
        apellido = "de Cervantes",
        seudonimo = "El manco",
        idiomaNativo = Lenguaje.ESPANIOL,
        fechaNacimiento = LocalDate.of(1668, 6, 9),
        premios= mutableListOf()
    )

    val borges = Autor(
        nombre = "Jorge Luis",
        apellido = "Borges",
        seudonimo = "cieguito",
        idiomaNativo = Lenguaje.ESPANIOL,
        fechaNacimiento = LocalDate.of(1968, 6, 9),
        premios= mutableListOf()
    )

    val king = Autor(
        nombre = "Stephen",
        apellido = "King",
        seudonimo = "miedo",
        idiomaNativo = Lenguaje.INGLES,
        fechaNacimiento = LocalDate.of(1948, 6, 9),
        premios= mutableListOf()
    )

    val listaAutores = mutableListOf(cervantes, borges, king)

    val agregador = AgregadorAutores(listaAutores)

    val mockedEnviaMail = mockk<EnviaMail>(relaxUnitFun = true)

    agregador.enviadorMail = mockedEnviaMail

    describe("Dada una acción del AgregadorAutores...") {
        it("Si se pasa la acción con la lista de tres autores, la lista de autores del repo debe ser de 3, y poder tomarlos por id") {
            // Act
            repositorioAutores.ejecutarAccion(agregador)

            // Assert
            repositorioAutores.itemPorId(1u) shouldBe cervantes
            repositorioAutores.itemPorId(2u) shouldBe borges
            repositorioAutores.itemPorId(3u) shouldBe king

        }
        it("Si se ejecuta la acción, envía mail") {
            // Act
            repositorioAutores.ejecutarAccion(agregador)
            // Assert
            verify(exactly = 1) {// Verifico que se haya enviado el mail
                mockedEnviaMail.envioMail(
                    Mail(
                        from = "Notificaciones@readapp.com.ar",
                        to = "admin@readapp.com.ar",
                        subject = "Ejecución de Agregador de autores",
                        content = "Se realizó el proceso: Agregador de autores"
                    )
                )
            }
        }
        repositorioAutores.limpiarParaTest()
    }
})