package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.*
import ar.edu.unsam.algo3.repos.RepositorioAutores
import ar.edu.unsam.algo3.repos.RepositorioLibros
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones
import ar.edu.unsam.algo3.repos.RepositoriosUsuarios
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DataInitializer(
    @Autowired val repoBook: RepositorioLibros,
    @Autowired val repoAutor: RepositorioAutores,
    @Autowired val repoRecom: RepositorioRecomendaciones,
    @Autowired val repoUser: RepositoriosUsuarios
) : CommandLineRunner {

    override fun run(vararg args: String?) {

        val borges = Autor(
            nombre = "Jorge Luis",
            apellido = "Borges",
            seudonimo = "cieguito",
            idiomaNativo = Lenguaje.ESPANIOL,
            fechaNacimiento = LocalDate.of(1978, 6, 1)
        )
        val cortazar = Autor(
            nombre = "Julio",
            apellido = "Cortázar",
            seudonimo = "Julito",
            idiomaNativo = Lenguaje.INGLES,
            fechaNacimiento = LocalDate.of(1975, 11, 5)
        )
        val stamateas = Autor(
            nombre = "Bernardo",
            apellido = "Stamateas",
            seudonimo = "berni",
            idiomaNativo = Lenguaje.ESPANIOL,
            fechaNacimiento = LocalDate.of(1985, 12, 9)
        )
        val hirsch = Autor(
            nombre = "Alex",
            apellido = "Hirsch",
            seudonimo = "elAlex",
            idiomaNativo = Lenguaje.ESPANIOL,
            fechaNacimiento = LocalDate.of(1965, 5, 10)
        )
        val toriyama = Autor(
            nombre = "Akira",
            apellido = "Toriyama",
            seudonimo = "tori",
            idiomaNativo = Lenguaje.FRANCES,
            fechaNacimiento = LocalDate.of(1955, 3, 10)
        )
        val Gombrich = Autor(
            nombre = "Ernst",
            apellido = "Gombrich",
            seudonimo = "gom",
            idiomaNativo = Lenguaje.INGLES,
            fechaNacimiento = LocalDate.of(1955, 3, 10)
        )
        val nik = Autor(
            nombre = "Nicolas",
            apellido = "Blandi",
            seudonimo = "nik",
            idiomaNativo = Lenguaje.ESPANIOL,
            fechaNacimiento = LocalDate.of(1955, 3, 10)
        )
        val lovecraft = Autor(
            nombre = "Howard Phillips",
            apellido = "Lovecraft",
            seudonimo = "HP",
            idiomaNativo = Lenguaje.INGLES,
            fechaNacimiento = LocalDate.of(1955, 3, 10)
        )

        repoAutor.crearItem(borges)
        repoAutor.crearItem(cortazar)
        repoAutor.crearItem(stamateas)
        repoAutor.crearItem(hirsch)
        repoAutor.crearItem(toriyama)
        repoAutor.crearItem(Gombrich)
        repoAutor.crearItem(nik)
        repoAutor.crearItem(lovecraft)

        val historiaDelArte = Libro(
            titulo = "Historia Del Arte",
            autor = Gombrich,
            paginas = 700,
            palabras = 10_000,
            ediciones = 2,
            ventasSemanales = 100,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ITALIANO),
        )

        val gaturro = Libro(
            titulo = "Gaturro Gigante",
            autor = nik,
            paginas = 105,
            palabras = 12_145,
            ediciones = 4,
            ventasSemanales = 54,
            lecturaCompleja = false,
            traducciones = mutableSetOf()
        )

        val dragonBall = Libro(
            titulo = "Dragon Ball",
            autor = toriyama,
            paginas = 360,
            palabras = 23_000,
            ediciones = 8,
            ventasSemanales = 223,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL,Lenguaje.INGLES,Lenguaje.ITALIANO)
        )

        val cthulhu = Libro(
            titulo = "Llamada de Cthulhu",
            autor = lovecraft,
            paginas = 310,
            palabras = 17_540,
            ediciones = 3,
            ventasSemanales = 21,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ITALIANO,Lenguaje.ESPANIOL)
        )

        val bill = Libro(
            titulo = "El libro de Bill",
            autor = hirsch,
            paginas = 224,
            palabras = 15_000,
            ediciones = 2,
            ventasSemanales = 150,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.ARABE,Lenguaje.BENGALI),
        )

        val aleph = Libro(
            titulo = "Aleph",
            autor = borges,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES),
        )

        val rayuela = Libro(
            titulo = "Rayuela",
            autor = cortazar,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES),
        )

        val genteToxica = Libro(
            titulo = "Gente Toxica",
            autor = stamateas,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES)
        )

        repoBook.crearItem(aleph)
        repoBook.crearItem(rayuela)
        repoBook.crearItem(genteToxica)
        repoBook.crearItem(bill)
        repoBook.crearItem(dragonBall)
        repoBook.crearItem(historiaDelArte)
        repoBook.crearItem(gaturro)
        repoBook.crearItem(cthulhu)

        val homero = Usuario(
            nombre = "Homero",
            apellido = "Simpson",
            userName = "hsimpson",
            password = "mandarina",
            email = "homer@simps.com",
            fechaNacimiento = LocalDate.of(1968, 4, 4),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 300,
            amigos = mutableSetOf(),
            librosLeidos = mutableListOf(bill,genteToxica,aleph, rayuela)
        )

        val marge = Usuario(
            nombre = "Marge",
            apellido = "Bouvier",
            userName = "mBouvier",
            password = "mandarina",
            email = "marge@simps.com",
            fechaNacimiento = LocalDate.of(1970, 2, 15),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 300,
            amigos = mutableSetOf()
        )

        val bart = Usuario(
            nombre = "Bart",
            apellido = "Simpson",
            userName = "bsimpson",
            password = "mandarina",
            email = "bart@simps.com",
            fechaNacimiento = LocalDate.of(1989, 8, 25),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 300,
            amigos = mutableSetOf(),
            librosLeidos = mutableListOf(dragonBall,historiaDelArte,aleph)
        )
        val lisa = Usuario(
            nombre = "Lisa",
            apellido = "Simpson",
            userName = "lsimpson",
            password = "mandarina",
            email = "lisa@simps.com",
            fechaNacimiento = LocalDate.of(1990, 8, 24),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 300,
            amigos = mutableSetOf(homero, marge, bart),
            librosLeidos = mutableListOf(aleph, rayuela, genteToxica)
        )

        val selma = Usuario(
            nombre = "Selma",
            apellido = "Bouvier",
            userName = "sbouvier",
            password = "patty",
            email = "selma@simps.com",
            fechaNacimiento = LocalDate.of(1951,6,2),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.FRANCES,
            palabrasXMinuto = 250,
            amigos = mutableSetOf (marge, lisa),    // problema no puedo agregar amigos que creo abajo
            librosLeidos = mutableListOf(rayuela),
            librosALeer = mutableSetOf(genteToxica),
            autoresPreferidos = mutableSetOf(borges,cortazar),
        )
        val patty = Usuario(
            nombre = "Patty",
            apellido = "Bouvier",
            userName = "pbouvier",
            password = "selma",
            email = "patty@simps.com",
            fechaNacimiento = LocalDate.of(1951,6,2),
            perfil = Precavido(selma),
            lenguajeNativo = Lenguaje.FRANCES,
            palabrasXMinuto = 150,
            amigos = mutableSetOf (selma, marge,lisa),
            librosLeidos = mutableListOf(aleph),
            librosALeer = mutableSetOf(genteToxica),
            autoresPreferidos = mutableSetOf(borges,stamateas),
        )

        val milhouse = Usuario(
            nombre = "Milhouse",
            apellido = "Van Houten",
            userName = "mvanhouten",
            password = "bluehair",
            email = "milhouse@simps.com",
            fechaNacimiento = LocalDate.of(1989,6,2),
            perfil = Precavido(selma),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 150,
            amigos = mutableSetOf (bart,homero, lisa),
            librosLeidos = mutableListOf(rayuela),
            librosALeer = mutableSetOf(genteToxica),
            autoresPreferidos = mutableSetOf(stamateas),
        )

        val barney = Usuario(
            nombre = "Barney",
            apellido = "Gumble",
            userName = "bgumble",
            password = "moe",
            email = "barney@simps.com",
            fechaNacimiento = LocalDate.of(1956,6,2),
            perfil = Nativista(selma),
            lenguajeNativo = Lenguaje.BENGALI,
            palabrasXMinuto = 150,
            amigos = mutableSetOf (homero, bart, selma),
            librosLeidos = mutableListOf(cthulhu,gaturro),
            librosALeer = mutableSetOf(genteToxica),
            autoresPreferidos = mutableSetOf(cortazar),
        )
        val nelson = Usuario(
            nombre = "Nelson",
            apellido = "Muntz",
            userName = "nmuntz",
            password = "brabucon",
            email = "nelson@simps.com",
            fechaNacimiento = LocalDate.of(1985,6,2),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.INGLES,
            palabrasXMinuto = 150,
            amigos = mutableSetOf (bart, lisa, marge, homero),
            librosLeidos = mutableListOf(genteToxica),
            librosALeer = mutableSetOf(aleph),
            autoresPreferidos = mutableSetOf(borges),
        )
        val carl = Usuario(
            nombre = "Carl",
            apellido = "Carlson",
            userName = "ccarlson",
            password = "lenny",
            email = "carl@simps.com",
            fechaNacimiento = LocalDate.of(1956,6,2),
            perfil = Leedor(),
            lenguajeNativo = Lenguaje.ARABE,
            palabrasXMinuto = 150,
            amigos = mutableSetOf (homero, bart, selma, marge, nelson),
            librosLeidos = mutableListOf(rayuela),
            librosALeer = mutableSetOf(aleph),
            autoresPreferidos = mutableSetOf(borges),
        )

        repoUser.crearItem(homero)
        repoUser.crearItem(marge)
        repoUser.crearItem(bart)
        repoUser.crearItem(lisa)
        repoUser.crearItem(selma)
        repoUser.crearItem(patty)
        repoUser.crearItem(barney)
        repoUser.crearItem(milhouse)
        repoUser.crearItem(nelson)
        repoUser.crearItem(carl)


        val valoracion = Valoracion(puntuacion = 5, comentario = "Buenisimo Bro!", autor = homero)
        val valoracion2 = Valoracion(puntuacion = 1, comentario = "Una porqueria!", autor = carl)

        val recomCompleta = Recomendacion(
            creador = lisa,
            resegna = "Estos libros están buenísimos!",
            libros = mutableSetOf(rayuela, genteToxica, aleph)
        )

        val recomCompletaConValoracion = Recomendacion(
            creador = lisa,
            resegna = "Estos libros están buenísimos, comenten!!",
            libros = mutableSetOf(rayuela),
            publica = true
        )

        recomCompletaConValoracion.agregarValoracion(valoracion)
        recomCompletaConValoracion.agregarValoracion(valoracion2)

        val recomCompleta2 = Recomendacion(
            creador = homero,
            resegna = "Estos libros están buenísimos 2222!",
            libros = mutableSetOf(aleph,genteToxica,bill)
        )

        val recomCompleta3 = Recomendacion(
            creador = barney,
            resegna = "Estos libros están buenísimos3333!",
            libros = mutableSetOf(cthulhu,gaturro),
            publica = true
        )

        val recomCompleta4 = Recomendacion(
            creador = bart,
            resegna = "Estos libros están buenísimos 4444!",
            libros = mutableSetOf(dragonBall,historiaDelArte,aleph),
            publica = true
        )

        repoRecom.crearItem(recomCompleta)
        repoRecom.crearItem(recomCompleta2)
        repoRecom.crearItem(recomCompleta3)
        repoRecom.crearItem(recomCompleta4)
        repoRecom.crearItem(recomCompletaConValoracion)
    }

}