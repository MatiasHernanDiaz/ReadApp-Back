import ar.edu.unsam.algo3.*
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDate

class EnviaMailSpec : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val creadorRecomendacion = Usuario(
        nombre = "Pedro",
        apellido = "Picapiedras",
        userName = "piedra",
        email = "piedra@gmail.com",
        fechaNacimiento = LocalDate.of(1990, 8, 24),
        perfil = Leedor(),
        lenguajeNativo = Lenguaje.ESPANIOL,
        palabrasXMinuto = 300,
        amigos = mutableSetOf()
    )

    val amigoDelCreador = Usuario(
        nombre = "Juan Luis",
        apellido = "Guerra",
        userName = "pez",
        email = "pez@gmail.com",
        fechaNacimiento = LocalDate.of(1988, 8, 15),
        perfil = Leedor(),
        lenguajeNativo = Lenguaje.ESPANIOL,
        palabrasXMinuto = 300,
        amigos = mutableSetOf()
    )

    creadorRecomendacion.agregarAmigo(amigoDelCreador)

    val autorPreferido = Autor(
        nombre = "Jorge Luis",
        apellido = "Borges",
        seudonimo = "cieguito",
        idiomaNativo = Lenguaje.ESPANIOL,
        fechaNacimiento = LocalDate.of(1978, 6, 1)
    )

    val libro1 = Libro(
        titulo = "Aleph",
        autor = autorPreferido,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES),
    )

    val nuevoLibro = Libro(
        titulo = "Rayuela",
        autor = autorPreferido,
        paginas = 180,
        palabras = 40_000,
        ediciones = 4,
        ventasSemanales = 120,
        lecturaCompleja = false,
        traducciones = mutableSetOf(Lenguaje.INGLES),
    )

    creadorRecomendacion.agregarLibroLeido(libro1)
    creadorRecomendacion.agregarLibroLeido(nuevoLibro)
    amigoDelCreador.agregarLibroLeido(libro1)
    amigoDelCreador.agregarLibroLeido(nuevoLibro)

    val recomCompleta = Recomendacion(
        creador = creadorRecomendacion,
        resegna = "Estos libros están buenísimos!",
        libros = mutableSetOf(libro1),
    )
    val mockedEnviaMail = mockk<EnviaMail>(relaxUnitFun = true)
    val mailFelicitaciones = NotificacionUsuario(mockedEnviaMail,recomCompleta)
    recomCompleta.agregarAddLibrosObserver(mailFelicitaciones)

    it("El usuario no es el creador") {
        recomCompleta.agregarLibro(amigoDelCreador,nuevoLibro)// Agrego el libro y notificamos a los observadores

        verify(exactly = 1) {// Verifico que se haya enviado el mail
            mockedEnviaMail.envioMail(
                Mail(
                    from = "Notificaciones@readapp.com.ar",
                    to = recomCompleta.creador().getMail(),
                    subject = "Se agregó un Libro",
                    content = "El usuario: ${amigoDelCreador.nombreCompleto()} agregó el Libro ${nuevoLibro.titulo()} " +
                            "a la recomendación que tenía estos " +
                            "Títulos: ${recomCompleta.libros().joinToString(", ") { it.titulo() }}"
                )
            )
        }
    }
})