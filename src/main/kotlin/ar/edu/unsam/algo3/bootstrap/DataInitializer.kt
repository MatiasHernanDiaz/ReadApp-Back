package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.*
import ar.edu.unsam.algo3.repos.RepositorioAutores
import ar.edu.unsam.algo3.repos.RepositorioLibros
import ar.edu.unsam.algo3.repos.RepositorioRecomendaciones
import ar.edu.unsam.algo3.repos.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DataInitializer(
    @Autowired val repoBook: RepositorioLibros,
    @Autowired val repoAutor: RepositorioAutores,
    @Autowired val repoRecom: RepositorioRecomendaciones,
    @Autowired val repoUser: UserRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {

        val borges = Autor(
            nombre = "Jorge Luis",
            apellido = "Borges",
            seudonimo = "cieguito",
            idiomaNativo = Language.SPANISH,
            fechaNacimiento = LocalDate.of(1978, 6, 1)
        )
        val cortazar = Autor(
            nombre = "Julio",
            apellido = "Cortázar",
            seudonimo = "Julito",
            idiomaNativo = Language.ENGLISH,
            fechaNacimiento = LocalDate.of(1975, 11, 5)
        )
        val stamateas = Autor(
            nombre = "Bernardo",
            apellido = "Stamateas",
            seudonimo = "berni",
            idiomaNativo = Language.SPANISH,
            fechaNacimiento = LocalDate.of(1985, 12, 9)
        )
        val hirsch = Autor(
            nombre = "Alex",
            apellido = "Hirsch",
            seudonimo = "elAlex",
            idiomaNativo = Language.SPANISH,
            fechaNacimiento = LocalDate.of(1965, 5, 10)
        )
        val toriyama = Autor(
            nombre = "Akira",
            apellido = "Toriyama",
            seudonimo = "tori",
            idiomaNativo = Language.FRENCH,
            fechaNacimiento = LocalDate.of(1955, 3, 10)
        )
        val Gombrich = Autor(
            nombre = "Ernst",
            apellido = "Gombrich",
            seudonimo = "gom",
            idiomaNativo = Language.ENGLISH,
            fechaNacimiento = LocalDate.of(1955, 3, 10)
        )
        val nik = Autor(
            nombre = "Nicolas",
            apellido = "Blandi",
            seudonimo = "nik",
            idiomaNativo = Language.SPANISH,
            fechaNacimiento = LocalDate.of(1955, 3, 10)
        )
        val lovecraft = Autor(
            nombre = "Howard Phillips",
            apellido = "Lovecraft",
            seudonimo = "HP",
            idiomaNativo = Language.ENGLISH,
            fechaNacimiento = LocalDate.of(1955, 3, 10)
        )

        repoAutor.createItem(borges)
        repoAutor.createItem(cortazar)
        repoAutor.createItem(stamateas)
        repoAutor.createItem(hirsch)
        repoAutor.createItem(toriyama)
        repoAutor.createItem(Gombrich)
        repoAutor.createItem(nik)
        repoAutor.createItem(lovecraft)

        val historiaDelArte = Libro(
            titulo = "Historia Del Arte",
            autor = Gombrich,
            paginas = 700,
            palabras = 10_000,
            ediciones = 2,
            ventasSemanales = 100,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ITALIAN),
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
            traducciones = mutableSetOf(Language.SPANISH,Language.ENGLISH,Language.ITALIAN)
        )

        val cthulhu = Libro(
            titulo = "Llamada de Cthulhu",
            autor = lovecraft,
            paginas = 310,
            palabras = 17_540,
            ediciones = 3,
            ventasSemanales = 21,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ITALIAN,Language.SPANISH)
        )

        val bill = Libro(
            titulo = "El libro de Bill",
            autor = hirsch,
            paginas = 224,
            palabras = 15_000,
            ediciones = 2,
            ventasSemanales = 150,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ARAB,Language.BENGALI),
        )

        val aleph = Libro(
            titulo = "Aleph",
            autor = borges,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH),
        )

        val rayuela = Libro(
            titulo = "Rayuela",
            autor = cortazar,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH),
        )

        val genteToxica = Libro(
            titulo = "Gente Toxica",
            autor = stamateas,
            paginas = 180,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 120,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH)
        )

        repoBook.createItem(aleph)
        repoBook.createItem(rayuela)
        repoBook.createItem(genteToxica)
        repoBook.createItem(bill)
        repoBook.createItem(dragonBall)
        repoBook.createItem(historiaDelArte)
        repoBook.createItem(gaturro)
        repoBook.createItem(cthulhu)

        val homero = User(
            firstName = "Homero",
            lastName = "Simpson",
            username = "hsimpson",
            password = "mandarina",
            email = "homer@simps.com",
            birthday = LocalDate.of(1968, 4, 4),
//            searchCriteria = Leedor(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 300,
            friends = mutableSetOf(),
            readBooks = mutableListOf(bill,genteToxica,aleph)
        )

        val marge = User(
            firstName = "Marge",
            lastName = "Bouvier",
            username = "mBouvier",
            password = "mandarina",
            email = "marge@simps.com",
            birthday = LocalDate.of(1970, 2, 15),
//            searchCriteria = Leedor(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 300,
            friends = mutableSetOf()
        )

        val bart = User(
            firstName = "Bart",
            lastName = "Simpson",
            username = "bsimpson",
            password = "mandarina",
            email = "bart@simps.com",
            birthday = LocalDate.of(1989, 8, 25),
//            searchCriteria = Leedor(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 300,
            friends = mutableSetOf(),
            readBooks = mutableListOf(dragonBall,historiaDelArte,aleph)
        )
        val lisa = User(
            firstName = "Lisa",
            lastName = "Simpson",
            username = "lsimpson",
            password = "mandarina",
            email = "lisa@simps.com",
            birthday = LocalDate.of(1990, 8, 24),
//            searchCriteria = Leedor(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 300,
            friends = mutableSetOf(homero, marge, bart),
            readBooks = mutableListOf(aleph, rayuela, genteToxica)
        )

        val selma = User(
            firstName = "Selma",
            lastName = "Bouvier",
            username = "sbouvier",
            password = "patty",
            email = "selma@simps.com",
            birthday = LocalDate.of(1951,6,2),
//            searchCriteria = Poliglota(),
            nativeLanguage = Language.FRENCH,
            readTimeMinAvg = 250,
            friends = mutableSetOf (marge, lisa),    // problema no puedo agregar amigos que creo abajo
            readBooks = mutableListOf(rayuela),
            booksToRead = mutableSetOf(genteToxica),
            favouriteAuthors = mutableSetOf(borges,cortazar),
        )
        val patty = User(
            firstName = "Patty",
            lastName = "Bouvier",
            username = "pbouvier",
            password = "selma",
            email = "patty@simps.com",
            birthday = LocalDate.of(1951,6,2),
//            searchCriteria = Precavido(selma),
            nativeLanguage = Language.FRENCH,
            readTimeMinAvg = 150,
            friends = mutableSetOf (selma, marge,lisa),
            readBooks = mutableListOf(aleph),
            booksToRead = mutableSetOf(genteToxica),
            favouriteAuthors = mutableSetOf(borges,stamateas),
        )

        val milhouse = User(
            firstName = "Milhouse",
            lastName = "Van Houten",
            username = "mvanhouten",
            password = "bluehair",
            email = "milhouse@simps.com",
            birthday = LocalDate.of(1989,6,2),
//            searchCriteria = Precavido(selma),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 150,
            friends = mutableSetOf (bart,homero, lisa),
            readBooks = mutableListOf(rayuela),
            booksToRead = mutableSetOf(genteToxica),
            favouriteAuthors = mutableSetOf(stamateas),
        )

        val barney = User(
            firstName = "Barney",
            lastName = "Gumble",
            username = "bgumble",
            password = "moe",
            email = "barney@simps.com",
            birthday = LocalDate.of(1956,6,2),
//            searchCriteria = Nativista(selma),
            nativeLanguage = Language.BENGALI,
            readTimeMinAvg = 150,
            friends = mutableSetOf (homero, bart, selma),
            readBooks = mutableListOf(cthulhu,gaturro),
            booksToRead = mutableSetOf(genteToxica),
            favouriteAuthors = mutableSetOf(cortazar),
        )
        val nelson = User(
            firstName = "Nelson",
            lastName = "Muntz",
            username = "nmuntz",
            password = "brabucon",
            email = "nelson@simps.com",
            birthday = LocalDate.of(1985,6,2),
//            searchCriteria = Leedor(),
            nativeLanguage = Language.ENGLISH,
            readTimeMinAvg = 150,
            friends = mutableSetOf (bart, lisa, marge, homero),
            readBooks = mutableListOf(genteToxica),
            booksToRead = mutableSetOf(aleph),
            favouriteAuthors = mutableSetOf(borges),
        )
        val carl = User(
            firstName = "Carl",
            lastName = "Carlson",
            username = "ccarlson",
            password = "lenny",
            email = "carl@simps.com",
            birthday = LocalDate.of(1956,6,2),
//            searchCriteria = Leedor(),
            nativeLanguage = Language.ARAB,
            readTimeMinAvg = 150,
            friends = mutableSetOf (homero, bart, selma, marge, nelson),
            readBooks = mutableListOf(rayuela),
            booksToRead = mutableSetOf(aleph),
            favouriteAuthors = mutableSetOf(borges),
        )

        repoUser.createItem(homero)
        repoUser.createItem(marge)
        repoUser.createItem(bart)
        repoUser.createItem(lisa)
        repoUser.createItem(selma)
        repoUser.createItem(patty)
        repoUser.createItem(barney)
        repoUser.createItem(milhouse)
        repoUser.createItem(nelson)
        repoUser.createItem(carl)


        val valoracion = Valoracion(puntuacion = 5, comentario = "Buenisimo Bro!", autor = homero)

        val recomCompleta = Recomendacion(
            creador = lisa,
            resegna = "Estos libros están buenísimos!",
            libros = mutableSetOf(rayuela, genteToxica, aleph)
        )

        val recomCompleta2 = Recomendacion(
            creador = homero,
            resegna = "Estos libros están buenísimos 2222!",
            libros = mutableSetOf(aleph,genteToxica,bill)
        )

        val recomCompleta3 = Recomendacion(
            creador = barney,
            resegna = "Estos libros están buenísimos3333!",
            libros = mutableSetOf(cthulhu,gaturro)
        )

        val recomCompleta4 = Recomendacion(
            creador = bart,
            resegna = "Estos libros están buenísimos 4444!",
            libros = mutableSetOf(dragonBall,historiaDelArte,aleph)
        )

        repoRecom.createItem(recomCompleta)
        repoRecom.createItem(recomCompleta2)
        repoRecom.createItem(recomCompleta3)
        repoRecom.createItem(recomCompleta4)
    }

}