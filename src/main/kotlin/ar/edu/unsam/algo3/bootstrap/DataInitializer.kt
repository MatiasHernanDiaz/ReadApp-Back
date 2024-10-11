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

        val borges = Author(
            firstName = "Jorge Luis",
            lastName = "Borges",
            alias = "cieguito",
            nativeLanguage = Language.SPANISH,
            birthday = LocalDate.of(1978, 6, 1)
        )
        val cortazar = Author(
            firstName = "Julio",
            lastName = "Cortázar",
            alias = "Julito",
            nativeLanguage = Language.ENGLISH,
            birthday = LocalDate.of(1975, 11, 5)
        )
        val stamateas = Author(
            firstName = "Bernardo",
            lastName = "Stamateas",
            alias = "berni",
            nativeLanguage = Language.SPANISH,
            birthday = LocalDate.of(1985, 12, 9)
        )
        val hirsch = Author(
            firstName = "Alex",
            lastName = "Hirsch",
            alias = "elAlex",
            nativeLanguage = Language.SPANISH,
            birthday = LocalDate.of(1965, 5, 10)
        )
        val toriyama = Author(
            firstName = "Akira",
            lastName = "Toriyama",
            alias = "tori",
            nativeLanguage = Language.FRENCH,
            birthday = LocalDate.of(1955, 3, 10)
        )
        val Gombrich = Author(
            firstName = "Ernst",
            lastName = "Gombrich",
            alias = "gom",
            nativeLanguage = Language.ENGLISH,
            birthday = LocalDate.of(1955, 3, 10)
        )
        val nik = Author(
            firstName = "Nicolas",
            lastName = "Blandi",
            alias = "nik",
            nativeLanguage = Language.SPANISH,
            birthday = LocalDate.of(1955, 3, 10)
        )
        val lovecraft = Author(
            firstName = "Howard Phillips",
            lastName = "Lovecraft",
            alias = "HP",
            nativeLanguage = Language.ENGLISH,
            birthday = LocalDate.of(1955, 3, 10)
        )

        val marquez = Author(
            firstName = "Gabriel",
            lastName = "García Márquez",
            alias = "Gabo",
            nativeLanguage = Language.SPANISH,
            birthday = LocalDate.of(1927, 3, 6)
        )

        val cervantes = Author(
            firstName = "Miguel",
            lastName = "de Cervantes",
            alias = "",
            nativeLanguage = Language.SPANISH,
            birthday = LocalDate.of(1547, 9, 29)
        )

        val shakespeare = Author(
            firstName = "William",
            lastName = "Shakespeare",
            alias = "",
            nativeLanguage = Language.ENGLISH,
            birthday = LocalDate.of(1564, 4, 26)
        )

        val tolstoy = Author(
            firstName = "Leo",
            lastName = "Tolstoy",
            alias = "",
            nativeLanguage = Language.RUSSIAN,
            birthday = LocalDate.of(1828, 9, 9)
        )

        val austen = Author(
            firstName = "Jane",
            lastName = "Austen",
            alias = "",
            nativeLanguage = Language.ENGLISH,
            birthday = LocalDate.of(1775, 12, 16)
        )

        val kafka = Author(
            firstName = "Franz",
            lastName = "Kafka",
            alias = "",
            nativeLanguage = Language.GERMAN,
            birthday = LocalDate.of(1883, 7, 3)
        )

        val woolf = Author(
            firstName = "Virginia",
            lastName = "Woolf",
            alias = "",
            nativeLanguage = Language.ENGLISH,
            birthday = LocalDate.of(1882, 1, 25)
        )

        val dostoevsky = Author(
            firstName = "Fyodor",
            lastName = "Dostoevsky",
            alias = "",
            nativeLanguage = Language.RUSSIAN,
            birthday = LocalDate.of(1821, 11, 11)
        )

        val orwell = Author(
            firstName = "George",
            lastName = "Orwell",
            alias = "Eric Arthur Blair",
            nativeLanguage = Language.ENGLISH,
            birthday = LocalDate.of(1903, 6, 25)
        )

        repoAutor.createItem(borges)
        repoAutor.createItem(cortazar)
        repoAutor.createItem(stamateas)
        repoAutor.createItem(hirsch)
        repoAutor.createItem(toriyama)
        repoAutor.createItem(Gombrich)
        repoAutor.createItem(nik)
        repoAutor.createItem(lovecraft)
        repoAutor.createItem(marquez)
        repoAutor.createItem(orwell)
        repoAutor.createItem(dostoevsky)
        repoAutor.createItem(woolf)
        repoAutor.createItem(kafka)
        repoAutor.createItem(austen)
        repoAutor.createItem(tolstoy)
        repoAutor.createItem(shakespeare)
        repoAutor.createItem(cervantes)

        
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
        
// Borges
        val ficciones = Libro(
            titulo = "Ficciones",
            autor = borges,
            paginas = 200,
            palabras = 45_000,
            ediciones = 6,
            ventasSemanales = 150,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.SPANISH, Language.ENGLISH)
        )
        
        val elInformeDeBrodie = Libro(
            titulo = "El informe de Brodie",
            autor = borges,
            paginas = 160,
            palabras = 38_000,
            ediciones = 3,
            ventasSemanales = 100,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.SPANISH, Language.FRENCH)
        )

        val elLibroDeArena = Libro(
            titulo = "El libro de arena",
            autor = borges,
            paginas = 190,
            palabras = 42_000,
            ediciones = 5,
            ventasSemanales = 110,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.SPANISH, Language.ENGLISH, Language.GERMAN)
        )

        val historiaUniversalDeLaInfamia = Libro(
            titulo = "Historia universal de la infamia",
            autor = borges,
            paginas = 150,
            palabras = 37_000,
            ediciones = 2,
            ventasSemanales = 90,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.SPANISH, Language.PORTUGUESE)
        )


// Gabriel García Márquez
        val cienAnosDeSoledad = Libro(
            titulo = "Cien años de soledad",
            autor = marquez,
            paginas = 500,
            palabras = 150_000,
            ediciones = 10,
            ventasSemanales = 500,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.SPANISH, Language.ENGLISH, Language.FRENCH)
        )


        val elAmorEnLosTiemposDelColera = Libro(
            titulo = "El amor en los tiempos del cólera",
            autor = marquez,
            paginas = 420,
            palabras = 120_000,
            ediciones = 8,
            ventasSemanales = 350,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.SPANISH, Language.ITALIAN)
        )

        val cronicaDeUnaMuerteAnunciada = Libro(
            titulo = "Crónica de una muerte anunciada",
            autor = marquez,
            paginas = 180,
            palabras = 50_000,
            ediciones = 5,
            ventasSemanales = 180,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.SPANISH, Language.ENGLISH)
        )


        val elOtonoDelPatriarca = Libro(
            titulo = "El otoño del patriarca",
            autor = marquez,
            paginas = 300,
            palabras = 80_000,
            ediciones = 4,
            ventasSemanales = 200,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.SPANISH, Language.PORTUGUESE)
        )


        val laHojarasca = Libro(
            titulo = "La hojarasca",
            autor = marquez,
            paginas = 220,
            palabras = 70_000,
            ediciones = 3,
            ventasSemanales = 150,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.SPANISH)
        )



        // Miguel de Cervantes
        val donQuijote = Libro(
            titulo = "Don Quijote de la Mancha",
            autor = cervantes,
            paginas = 900,
            palabras = 380_000,
            ediciones = 20,
            ventasSemanales = 1000,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.SPANISH, Language.ENGLISH, Language.FRENCH)
        )


        val lasNovelasEjemplares = Libro(
            titulo = "Las novelas ejemplares",
            autor = cervantes,
            paginas = 400,
            palabras = 120_000,
            ediciones = 8,
            ventasSemanales = 250,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.SPANISH, Language.ITALIAN)
        )


        val losTrabajosDePersiles = Libro(
            titulo = "Los trabajos de Persiles y Sigismunda",
            autor = cervantes,
            paginas = 600,
            palabras = 200_000,
            ediciones = 7,
            ventasSemanales = 300,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.SPANISH, Language.GERMAN)
        )

        val laGalatea = Libro(
            titulo = "La Galatea",
            autor = cervantes,
            paginas = 350,
            palabras = 100_000,
            ediciones = 4,
            ventasSemanales = 180,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.SPANISH, Language.PORTUGUESE)
        )

        val elViajeDelParnaso = Libro(
            titulo = "El viaje del Parnaso",
            autor = cervantes,
            paginas = 280,
            palabras = 85_000,
            ediciones = 3,
            ventasSemanales = 120,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.SPANISH)
        )

        // William Shakespeare
        val hamlet = Libro(
            titulo = "Hamlet",
            autor = shakespeare,
            paginas = 320,
            palabras = 95_000,
            ediciones = 10,
            ventasSemanales = 500,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.SPANISH)
        )


        val macbeth = Libro(
            titulo = "Macbeth",
            autor = shakespeare,
            paginas = 240,
            palabras = 80_000,
            ediciones = 8,
            ventasSemanales = 400,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.ITALIAN)
        )


        val othello = Libro(
            titulo = "Othello",
            autor = shakespeare,
            paginas = 280,
            palabras = 85_000,
            ediciones = 7,
            ventasSemanales = 350,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.FRENCH)
        )

        val romeoYJulieta = Libro(
            titulo = "Romeo y Julieta",
            autor = shakespeare,
            paginas = 260,
            palabras = 78_000,
            ediciones = 6,
            ventasSemanales = 300,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH, Language.SPANISH)
        )

        val julioCesar = Libro(
            titulo = "Julio César",
            autor = shakespeare,
            paginas = 250,
            palabras = 75_000,
            ediciones = 5,
            ventasSemanales = 250,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.GERMAN)
        )

        // Leo Tolstoy
        val guerraYPaz = Libro(
            titulo = "Guerra y Paz",
            autor = tolstoy,
            paginas = 1225,
            palabras = 560_000,
            ediciones = 12,
            ventasSemanales = 900,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.RUSSIAN, Language.ENGLISH, Language.FRENCH)
        )

        val annaKarenina = Libro(
            titulo = "Anna Karenina",
            autor = tolstoy,
            paginas = 864,
            palabras = 340_000,
            ediciones = 10,
            ventasSemanales = 750,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.RUSSIAN, Language.ENGLISH)
        )

        val resurreccion = Libro(
            titulo = "Resurrección",
            autor = tolstoy,
            paginas = 600,
            palabras = 260_000,
            ediciones = 8,
            ventasSemanales = 500,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.RUSSIAN, Language.ITALIAN)
        )

        val laMuerteDeIvanIlich = Libro(
            titulo = "La muerte de Iván Ilich",
            autor = tolstoy,
            paginas = 140,
            palabras = 50_000,
            ediciones = 5,
            ventasSemanales = 300,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.RUSSIAN, Language.SPANISH)
        )

        val hadjiMurad = Libro(
            titulo = "Hadji Murad",
            autor = tolstoy,
            paginas = 240,
            palabras = 80_000,
            ediciones = 4,
            ventasSemanales = 250,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.RUSSIAN, Language.ENGLISH)
        )


// Jane Austen
        val orgulloYPrejuicio = Libro(
            titulo = "Orgullo y prejuicio",
            autor = austen,
            paginas = 432,
            palabras = 120_000,
            ediciones = 10,
            ventasSemanales = 600,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH, Language.SPANISH)
        )

        val sentidoYSensibilidad = Libro(
            titulo = "Sentido y sensibilidad",
            autor = austen,
            paginas = 368,
            palabras = 100_000,
            ediciones = 8,
            ventasSemanales = 500,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH, Language.ITALIAN)
        )

        val emma = Libro(
            titulo = "Emma",
            autor = austen,
            paginas = 512,
            palabras = 150_000,
            ediciones = 9,
            ventasSemanales = 550,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH, Language.FRENCH)
        )

        val mansfieldPark = Libro(
            titulo = "Mansfield Park",
            autor = austen,
            paginas = 400,
            palabras = 130_000,
            ediciones = 6,
            ventasSemanales = 400,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH, Language.SPANISH)
        )

        val persuasion = Libro(
            titulo = "Persuasión",
            autor = austen,
            paginas = 288,
            palabras = 90_000,
            ediciones = 7,
            ventasSemanales = 350,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH, Language.PORTUGUESE)
        )


// Franz Kafka
        val laMetamorfosis = Libro(
            titulo = "La metamorfosis",
            autor = kafka,
            paginas = 96,
            palabras = 22_000,
            ediciones = 5,
            ventasSemanales = 450,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.GERMAN, Language.ENGLISH)
        )

        val elProceso = Libro(
            titulo = "El proceso",
            autor = kafka,
            paginas = 320,
            palabras = 85_000,
            ediciones = 8,
            ventasSemanales = 350,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.GERMAN, Language.SPANISH)
        )

        val elCastillo = Libro(
            titulo = "El castillo",
            autor = kafka,
            paginas = 400,
            palabras = 100_000,
            ediciones = 7,
            ventasSemanales = 320,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.GERMAN, Language.FRENCH)
        )

        val cartasAlPadre = Libro(
            titulo = "Cartas al padre",
            autor = kafka,
            paginas = 240,
            palabras = 60_000,
            ediciones = 4,
            ventasSemanales = 280,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.GERMAN, Language.ENGLISH)
        )

        val enLaColoniaPenal = Libro(
            titulo = "En la colonia penal",
            autor = kafka,
            paginas = 140,
            palabras = 35_000,
            ediciones = 3,
            ventasSemanales = 200,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.GERMAN, Language.SPANISH)
        )


// Virginia Woolf
        val laSenoraDalloway = Libro(
            titulo = "La señora Dalloway",
            autor = woolf,
            paginas = 304,
            palabras = 75_000,
            ediciones = 6,
            ventasSemanales = 420,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.SPANISH)
        )

        val alFaro = Libro(
            titulo = "Al faro",
            autor = woolf,
            paginas = 209,
            palabras = 65_000,
            ediciones = 5,
            ventasSemanales = 350,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.FRENCH)
        )

        val lasOlas = Libro(
            titulo = "Las olas",
            autor = woolf,
            paginas = 297,
            palabras = 70_000,
            ediciones = 7,
            ventasSemanales = 380,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.ITALIAN)
        )

        val orlando = Libro(
            titulo = "Orlando",
            autor = woolf,
            paginas = 336,
            palabras = 80_000,
            ediciones = 6,
            ventasSemanales = 300,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.SPANISH)
        )

        val unaHabitacionPropia = Libro(
            titulo = "Una habitación propia",
            autor = woolf,
            paginas = 172,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 260,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH, Language.SPANISH)
        )


// Fyodor Dostoevsky
        val crimenYCastigo = Libro(
            titulo = "Crimen y castigo",
            autor = dostoevsky,
            paginas = 671,
            palabras = 180_000,
            ediciones = 10,
            ventasSemanales = 700,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.RUSSIAN, Language.ENGLISH, Language.SPANISH)
        )

        val elIdiota = Libro(
            titulo = "El idiota",
            autor = dostoevsky,
            paginas = 656,
            palabras = 170_000,
            ediciones = 8,
            ventasSemanales = 550,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.RUSSIAN, Language.ITALIAN)
        )

        val losHermanosKaramazov = Libro(
            titulo = "Los hermanos Karamazov",
            autor = dostoevsky,
            paginas = 824,
            palabras = 200_000,
            ediciones = 10,
            ventasSemanales = 600,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.RUSSIAN, Language.FRENCH)
        )

        val demonios = Libro(
            titulo = "Demonios",
            autor = dostoevsky,
            paginas = 768,
            palabras = 190_000,
            ediciones = 7,
            ventasSemanales = 500,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.RUSSIAN, Language.ENGLISH)
        )

        val elJugador = Libro(
            titulo = "El jugador",
            autor = dostoevsky,
            paginas = 240,
            palabras = 60_000,
            ediciones = 5,
            ventasSemanales = 350,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.RUSSIAN, Language.SPANISH)
        )


// George Orwell
        val _1984 = Libro(
            titulo = "1984",
            autor = orwell,
            paginas = 328,
            palabras = 90_000,
            ediciones = 12,
            ventasSemanales = 900,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.SPANISH)
        )

        val rebelionEnLaGranja = Libro(
            titulo = "Rebelión en la granja",
            autor = orwell,
            paginas = 112,
            palabras = 30_000,
            ediciones = 10,
            ventasSemanales = 700,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH, Language.FRENCH)
        )

        val homenajeACataluna = Libro(
            titulo = "Homenaje a Cataluña",
            autor = orwell,
            paginas = 232,
            palabras = 60_000,
            ediciones = 6,
            ventasSemanales = 400,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.ITALIAN)
        )

        val losDiasDeBirmania = Libro(
            titulo = "Los días de Birmania",
            autor = orwell,
            paginas = 288,
            palabras = 75_000,
            ediciones = 5,
            ventasSemanales = 300,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Language.ENGLISH, Language.SPANISH)
        )

        val sinBlancaEnParisYLondres = Libro(
            titulo = "Sin blanca en París y Londres",
            autor = orwell,
            paginas = 232,
            palabras = 60_000,
            ediciones = 4,
            ventasSemanales = 250,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Language.ENGLISH, Language.ITALIAN)
        )

        repoBook.createItem(aleph)
        repoBook.createItem(rayuela)
        repoBook.createItem(genteToxica)
        repoBook.createItem(bill)
        repoBook.createItem(dragonBall)
        repoBook.createItem(historiaDelArte)
        repoBook.createItem(gaturro)
        repoBook.createItem(cthulhu)
        repoBook.createItem(julioCesar)
        repoBook.createItem(romeoYJulieta)
        repoBook.createItem(othello)
        repoBook.createItem(macbeth)
        repoBook.createItem(hamlet)
        repoBook.createItem(elViajeDelParnaso)
        repoBook.createItem(laGalatea)
        repoBook.createItem(losTrabajosDePersiles)
        repoBook.createItem(lasNovelasEjemplares)
        repoBook.createItem(donQuijote)
        repoBook.createItem(laHojarasca)
        repoBook.createItem(elOtonoDelPatriarca)
        repoBook.createItem(cronicaDeUnaMuerteAnunciada)
        repoBook.createItem(cienAnosDeSoledad)
        repoBook.createItem(elAmorEnLosTiemposDelColera)
        repoBook.createItem(historiaUniversalDeLaInfamia)
        repoBook.createItem(elLibroDeArena)
        repoBook.createItem(elInformeDeBrodie)
        repoBook.createItem(ficciones)
        repoBook.createItem(sinBlancaEnParisYLondres)
        repoBook.createItem(_1984)
        repoBook.createItem(elJugador)
        repoBook.createItem(demonios)
        repoBook.createItem(losHermanosKaramazov)
        repoBook.createItem(elIdiota)
        repoBook.createItem(crimenYCastigo)
        repoBook.createItem(unaHabitacionPropia)
        repoBook.createItem(orlando)
        repoBook.createItem(lasOlas)
        repoBook.createItem(alFaro)
        repoBook.createItem(laSenoraDalloway)
        repoBook.createItem(enLaColoniaPenal)
        repoBook.createItem(cartasAlPadre)
        repoBook.createItem(elCastillo)
        repoBook.createItem(elProceso)
        repoBook.createItem(laMetamorfosis)
        repoBook.createItem(persuasion)
        repoBook.createItem(mansfieldPark)
        repoBook.createItem(emma)
        repoBook.createItem(sentidoYSensibilidad)
        repoBook.createItem(orgulloYPrejuicio)
        repoBook.createItem(hadjiMurad)
        repoBook.createItem(laMuerteDeIvanIlich)
        repoBook.createItem(resurreccion)
        repoBook.createItem(guerraYPaz)
        repoBook.createItem(annaKarenina)
        repoBook.createItem(rebelionEnLaGranja)
        repoBook.createItem(homenajeACataluna)
        repoBook.createItem(losDiasDeBirmania)
//        (aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
//        othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
//        donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
//        historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
//        demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
//        enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
//        orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
//        homenajeACataluna,losDiasDeBirmania)


        val homero = User(
            firstName = "Homero",
            lastName = "Simpson",
            username = "hsimpson",
            password = "mandarina",
            email = "homer@simps.com",
            birthday = LocalDate.of(1968, 4, 4),
            searchCriteria = GreatReader(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 300,
            friends = mutableSetOf(),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val marge = User(
            firstName = "Marge",
            lastName = "Bouvier",
            username = "mBouvier",
            password = "mandarina",
            email = "marge@simps.com",
            birthday = LocalDate.of(1970, 2, 15),
            searchCriteria = GreatReader(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 300,
            friends = mutableSetOf(),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val bart = User(
            firstName = "Bart",
            lastName = "Simpson",
            username = "bsimpson",
            password = "mandarina",
            email = "bart@simps.com",
            birthday = LocalDate.of(1989, 8, 25),
            searchCriteria = GreatReader(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 300,
            friends = mutableSetOf(),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
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
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val selma = User(
            firstName = "Selma",
            lastName = "Bouvier",
            username = "sbouvier",
            password = "patty",
            email = "selma@simps.com",
            birthday = LocalDate.of(1951,6,2),
//            searchCriteria = Polyglot(),
            nativeLanguage = Language.FRENCH,
            readTimeMinAvg = 250,
            friends = mutableSetOf (marge, lisa),    // problema no puedo agregar friends que creo abajo
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania),
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
//            searchCriteria = Cautious(selma),
            nativeLanguage = Language.FRENCH,
            readTimeMinAvg = 150,
            friends = mutableSetOf (selma, marge,lisa),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania),
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
//            searchCriteria = Cautious(selma),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 150,
            friends = mutableSetOf (bart,homero, lisa),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania),
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
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania),
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
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania),
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
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania),
            booksToRead = mutableSetOf(aleph),
            favouriteAuthors = mutableSetOf(borges),
        )


        val lenny = User(
            firstName = "Lenny",
            lastName = "Leonard",
            username = "lleonard",
            password = "mandarina",
            email = "lenny@simps.com",
            birthday = LocalDate.of(1970, 2, 10),
            searchCriteria = Polyglot(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 290,
            friends = mutableSetOf(carl, barney, milhouse),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val ned = User(
            firstName = "Ned",
            lastName = "Flanders",
            username = "nflanders",
            password = "mandarina",
            email = "ned@simps.com",
            birthday = LocalDate.of(1968, 5, 10),
            searchCriteria = Polyglot(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 260,
            friends = mutableSetOf(marge, selma, patty),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val skinner = User(
            firstName = "Seymour",
            lastName = "Skinner",
            username = "sskinner",
            password = "mandarina",
            email = "skinner@simps.com",
            birthday = LocalDate.of(1970, 1, 20),
            searchCriteria = Cautious(ned),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 240,
            friends = mutableSetOf(bart, nelson, lisa),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val ralph = User(
            firstName = "Ralph",
            lastName = "Wiggum",
            username = "rwiggum",
            password = "mandarina",
            email = "ralph@simps.com",
            birthday = LocalDate.of(1990, 4, 15),
            searchCriteria = Polyglot(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 130,
            friends = mutableSetOf(bart, milhouse, lisa),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val apu = User(
            firstName = "Apu",
            lastName = "Nahasapeemapetilon",
            username = "anahasapeemapetilon",
            password = "mandarina",
            email = "apu@simps.com",
            birthday = LocalDate.of(1975, 12, 12),
            searchCriteria = Polyglot(),
            nativeLanguage = Language.ENGLISH,
            readTimeMinAvg = 310,
            friends = mutableSetOf(marge, selma, patty),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val judy = User(
            firstName = "Judy",
            lastName = "Hopps",
            username = "jhopps",
            password = "mandarina",
            email = "judy@simps.com",
            birthday = LocalDate.of(1995, 3, 3),
            searchCriteria = Experiencied(),
            nativeLanguage = Language.ENGLISH,
            readTimeMinAvg = 300,
            friends = mutableSetOf(lisa, bart, milhouse),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val groundskeeper = User(
            firstName = "Willie",
            lastName = "MacDougal",
            username = "wmacdougal",
            password = "mandarina",
            email = "willie@simps.com",
            birthday = LocalDate.of(1965, 5, 18),
            searchCriteria = Polyglot(),
            nativeLanguage = Language.ENGLISH,
            readTimeMinAvg = 250,
            friends = mutableSetOf(bart, milhouse, nelson),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val krusty = User(
            firstName = "Krusty",
            lastName = "el Payaso",
            username = "kpayaso",
            password = "mandarina",
            email = "krusty@simps.com",
            birthday = LocalDate.of(1975, 8, 4),
            searchCriteria = Polyglot(),
            nativeLanguage = Language.SPANISH,
            readTimeMinAvg = 320,
            friends = mutableSetOf(homero, bart, milhouse),
            readBooks = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )


        nelson.addFriend(carl)
        carl.addFriend(lenny)
        barney.addFriend(carl)
        barney.addFriend(nelson)
        homero.addFriend(lisa)
        homero.addFriend(marge)
        homero.addFriend(carl)

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
        repoUser.createItem(krusty)
        repoUser.createItem(lenny)
        repoUser.createItem(groundskeeper)
        repoUser.createItem(ned)
        repoUser.createItem(skinner)
        repoUser.createItem(ralph)
        repoUser.createItem(judy)
        repoUser.createItem(apu)

        // Recomendaciones de Homero
        val recomHomero1 = Recomendacion(
            creador = homero,
            resegna = "Una emocionante aventura llena de risas y emoción.",
            libros = mutableSetOf(aleph, bill, genteToxica),
            publica = false,
            titulo = "Aventura y humor en un solo libro"
        )

        val recomHomero2 = Recomendacion(
            creador = homero,
            resegna = "Una obra maestra que no puedes dejar de leer.",
            libros = mutableSetOf(rayuela, aleph),
            publica = true,
            titulo = "Una historia para reflexionar"
        )

        val recomHomero3 = Recomendacion(
            creador = homero,
            resegna = "Un libro que te hará reír y llorar a la vez.",
            libros = mutableSetOf(genteToxica, aleph),
            publica = true,
            titulo = "La montaña rusa de emociones"
        )

// Recomendaciones de Lisa
        val recomLisa1 = Recomendacion(
            creador = lisa,
            resegna = "Una obra profundamente reflexiva que invita a la introspección.",
            libros = mutableSetOf(orlando, lasOlas),
            publica = false,
            titulo = "Reflexiones que tocan el alma"
        )

        val recomLisa2 = Recomendacion(
            creador = lisa,
            resegna = "Una narrativa impresionante que no puedes perderte.",
            libros = mutableSetOf(alFaro, orlando),
            publica = true,
            titulo = "Una lectura indispensable"
        )

        val recomLisa3 = Recomendacion(
            creador = lisa,
            resegna = "Un viaje emocional que desafía las convenciones.",
            libros = mutableSetOf(alFaro, lasOlas),
            publica = true,
            titulo = "Un libro que desafía la mente"
        )

// Recomendaciones de Bart
        val recomBart1 = Recomendacion(
            creador = bart,
            resegna = "Divertido y lleno de aventuras inesperadas.",
            libros = mutableSetOf(_1984, elProceso),
            publica = false,
            titulo = "Diversión garantizada"
        )

        val recomBart2 = Recomendacion(
            creador = bart,
            resegna = "Una lectura que te mantendrá al borde de tu asiento.",
            libros = mutableSetOf(elJugador, elProceso),
            publica = true,
            titulo = "Acción y risas"
        )

        val recomBart3 = Recomendacion(
            creador = bart,
            resegna = "Un clásico que nunca pasa de moda.",
            libros = mutableSetOf(elJugador, _1984,elProceso),
            publica = true,
            titulo = "Un clásico de la literatura"
        )

// Recomendaciones de Marge
        val recomMarge1 = Recomendacion(
            creador = marge,
            resegna = "Perfecto para leer en familia, lleno de enseñanzas.",
            libros = mutableSetOf(crimenYCastigo, orgulloYPrejuicio),
            publica = false,
            titulo = "Lecturas para compartir"
        )

        val recomMarge2 = Recomendacion(
            creador = marge,
            resegna = "Inspirador y conmovedor, ideal para reflexionar.",
            libros = mutableSetOf(orgulloYPrejuicio, rebelionEnLaGranja),
            publica = false,
            titulo = "Lecturas para el corazón"
        )

        val recomMarge3 = Recomendacion(
            creador = marge,
            resegna = "Una historia entrañable que toca el alma.",
            libros = mutableSetOf(rebelionEnLaGranja, orgulloYPrejuicio, crimenYCastigo),
            publica = true,
            titulo = "Cuentos que nos unen"
        )

// Recomendaciones de Selma
        val recomSelma1 = Recomendacion(
            creador = selma,
            resegna = "Interesante y educativo, un buen recurso.",
            libros = mutableSetOf(elCastillo, laMetamorfosis),
            publica = false,
            titulo = "Aprendiendo mientras leemos"
        )

        val recomSelma2 = Recomendacion(
            creador = selma,
            resegna = "Un relato intrigante que te atrapa desde el inicio.",
            libros = mutableSetOf(laMetamorfosis, guerraYPaz),
            publica = true,
            titulo = "Intriga y emoción"
        )

        val recomSelma3 = Recomendacion(
            creador = selma,
            resegna = "Un buen libro para pensar en nuestra sociedad.",
            libros = mutableSetOf(guerraYPaz, laMetamorfosis, elCastillo),
            publica = true,
            titulo = "Reflexionando sobre el mundo"
        )

// Recomendaciones de Patty
        val recomPatty1 = Recomendacion(
            creador = patty,
            resegna = "Una obra maestra que todo el mundo debería leer.",
            libros = mutableSetOf(alFaro, orlando),
            publica = false,
            titulo = "Una lectura obligada"
        )

        val recomPatty2 = Recomendacion(
            creador = patty,
            resegna = "Desafiando las normas, una lectura fascinante.",
            libros = mutableSetOf(orlando, orgulloYPrejuicio),
            publica = true,
            titulo = "Un desafío literario"
        )

        val recomPatty3 = Recomendacion(
            creador = patty,
            resegna = "Increíblemente divertido y lleno de giros.",
            libros = mutableSetOf(orlando, alFaro, orgulloYPrejuicio),
            publica = true,
            titulo = "Diversión literaria"
        )

// Recomendaciones de Milhouse
        val recomMilhouse1 = Recomendacion(
            creador = milhouse,
            resegna = "Un libro que mezcla la risa y la reflexión.",
            libros = mutableSetOf(losHermanosKaramazov, demonios),
            publica = false,
            titulo = "Risas y reflexiones"
        )

        val recomMilhouse2 = Recomendacion(
            creador = milhouse,
            resegna = "Ideal para un día de lectura en casa.",
            libros = mutableSetOf(demonios, elIdiota, losHermanosKaramazov),
            publica = true,
            titulo = "Un buen día de lectura"
        )

        val recomMilhouse3 = Recomendacion(
            creador = milhouse,
            resegna = "Aventuras locas que te harán reír.",
            libros = mutableSetOf(losHermanosKaramazov),
            publica = true,
            titulo = "Aventuras disparatadas"
        )

// Recomendaciones de Barney
        val recomBarney1 = Recomendacion(
            creador = barney,
            resegna = "Descripción de la reseña sobre la obra que toca el corazón.",
            libros = mutableSetOf(sinBlancaEnParisYLondres),
            publica = false,
            titulo = "Una serie de libros para llorar sin parar"
        )

        val recomBarney2 = Recomendacion(
            creador = barney,
            resegna = "Aventura y emoción en cada página.",
            libros = mutableSetOf(sinBlancaEnParisYLondres, rebelionEnLaGranja),
            publica = true,
            titulo = "La aventura de tu vida"
        )

        val recomBarney3 = Recomendacion(
            creador = barney,
            resegna = "Un relato que te hará reír y reflexionar.",
            libros = mutableSetOf(rebelionEnLaGranja, sinBlancaEnParisYLondres, elProceso),
            publica = true,
            titulo = "Diversión y reflexión"
        )

// Recomendaciones de Nelson
        val recomNelson1 = Recomendacion(
            creador = nelson,
            resegna = "Un libro que atrapa desde la primera página.",
            libros = mutableSetOf(_1984, laSenoraDalloway, enLaColoniaPenal),
            publica = false,
            titulo = "Atrapa y no suelta"
        )

        val recomNelson2 = Recomendacion(
            creador = nelson,
            resegna = "Intrigante y sorprendente, un gran libro.",
            libros = mutableSetOf(enLaColoniaPenal),
            publica = true,
            titulo = "Sorprendente y cautivador"
        )

        val recomNelson3 = Recomendacion(
            creador = nelson,
            resegna = "Una lectura profunda que invita a la reflexión.",
            libros = mutableSetOf(_1984),
            publica = true,
            titulo = "Reflexiones literarias"
        )

// Recomendaciones de Lenny
        val recomLenny1 = Recomendacion(
            creador = lenny,
            resegna = "Divertido y entretenido, lo disfruté.",
            libros = mutableSetOf(aleph, losHermanosKaramazov),
            publica = false,
            titulo = "Diversión sin fin"
        )

        val recomLenny2 = Recomendacion(
            creador = lenny,
            resegna = "Una mezcla perfecta de humor y trama.",
            libros = mutableSetOf(losHermanosKaramazov, demonios),
            publica = true,
            titulo = "Humor y trama en equilibrio"
        )

        val recomLenny3 = Recomendacion(
            creador = lenny,
            resegna = "Aventuras que nunca olvidarás.",
            libros = mutableSetOf(demonios, aleph, losHermanosKaramazov),
            publica = true,
            titulo = "Aventuras inolvidables"
        )

// Recomendaciones de Ned
        val recomNed1 = Recomendacion(
            creador = ned,
            resegna = "Un relato hermoso que toca el alma.",
            libros = mutableSetOf(crimenYCastigo, elProceso),
            publica = false,
            titulo = "Historias que emocionan"
        )

        val recomNed2 = Recomendacion(
            creador = ned,
            resegna = "Un viaje a través de la risa y la emoción.",
            libros = mutableSetOf(elProceso, orgulloYPrejuicio),
            publica = true,
            titulo = "Un viaje inolvidable"
        )

        val recomNed3 = Recomendacion(
            creador = ned,
            resegna = "Lectura esencial para el corazón.",
            libros = mutableSetOf(orgulloYPrejuicio, elProceso, crimenYCastigo),
            publica = true,
            titulo = "Lecturas para el alma"
        )

// Recomendaciones de Krusty
        val recomKrusty1 = Recomendacion(
            creador = krusty,
            resegna = "Una obra que combina diversión y emoción.",
            libros = mutableSetOf(losHermanosKaramazov, laMetamorfosis),
            publica = false,
            titulo = "Diversión y emoción"
        )

        val recomKrusty2 = Recomendacion(
            creador = krusty,
            resegna = "Una historia divertida que no olvidarás.",
            libros = mutableSetOf(laMetamorfosis, elJugador),
            publica = true,
            titulo = "Diversión sin límites"
        )

        val recomKrusty3 = Recomendacion(
            creador = krusty,
            resegna = "Una aventura hilarante que te hará reír.",
            libros = mutableSetOf(elJugador, laMetamorfosis, losHermanosKaramazov),
            publica = true,
            titulo = "Risas y aventuras"
        )

// Recomendaciones de Principal Skinner
        val recomSkinner1 = Recomendacion(
            creador = skinner,
            resegna = "Una lectura que combina educación y entretenimiento.",
            libros = mutableSetOf(_1984, laMuerteDeIvanIlich),
            publica = false,
            titulo = "Aprender y disfrutar"
        )

        val recomSkinner2 = Recomendacion(
            creador = skinner,
            resegna = "Una obra que todos deberían leer.",
            libros = mutableSetOf(laMuerteDeIvanIlich, orlando),
            publica = true,
            titulo = "Lectura imprescindible"
        )

        val recomSkinner3 = Recomendacion(
            creador = skinner,
            resegna = "Una mezcla perfecta de lecciones y diversión.",
            libros = mutableSetOf(orlando),
            publica = true,
            titulo = "Lecciones divertidas"
        )

// Recomendaciones de Ralph
        val recomRalph1 = Recomendacion(
            creador = ralph,
            resegna = "Una historia divertida y emocionante.",
            libros = mutableSetOf(guerraYPaz, laSenoraDalloway, sinBlancaEnParisYLondres),
            publica = false,
            titulo = "Diversión para todos"
        )

        val recomRalph2 = Recomendacion(
            creador = ralph,
            resegna = "Un libro que me hizo reír a carcajadas.",
            libros = mutableSetOf(sinBlancaEnParisYLondres, laSenoraDalloway),
            publica = true,
            titulo = "Risas garantizadas"
        )

        val recomRalph3 = Recomendacion(
            creador = ralph,
            resegna = "Una aventura inolvidable que todos deben leer.",
            libros = mutableSetOf(laSenoraDalloway, guerraYPaz),
            publica = true,
            titulo = "Una aventura que recordarás"
        )

        // Valoraciones de Homero
        val valoracionHomero1 = Valoracion(puntuacion = 5, comentario = "Buenísimo Bro!", autor = homero)
        val valoracionHomero2 = Valoracion(puntuacion = 4, comentario = "Muy interesante.", autor = homero)
        val valoracionHomero3 = Valoracion(puntuacion = 3, comentario = "Regular.", autor = homero)

// Agregando valoraciones a las recomendaciones de otros personajes
        recomLisa1.agregarValoracion(valoracionHomero1)  // Lisa valora la recomendación de Homero
        recomBart1.agregarValoracion(valoracionHomero2)  // Bart valora la recomendación de Homero
        recomMarge1.agregarValoracion(valoracionHomero3)  // Marge valora la recomendación de Homero

// Valoraciones de Lisa
        val valoracionLisa1 = Valoracion(puntuacion = 5, comentario = "Increíble.", autor = lisa)
        val valoracionLisa2 = Valoracion(puntuacion = 4, comentario = "Reflexivo y hermoso.", autor = lisa)
        val valoracionLisa3 = Valoracion(puntuacion = 3, comentario = "Bueno pero no es para todos.", autor = lisa)

// Agregando valoraciones a las recomendaciones de otros personajes
        recomBart2.agregarValoracion(valoracionLisa1)  // Bart valora la recomendación de Lisa
        recomBarney1.agregarValoracion(valoracionLisa2)  // Barney valora la recomendación de Lisa
        recomSelma1.agregarValoracion(valoracionLisa3)  // Selma valora la recomendación de Lisa

// Valoraciones de Bart
        val valoracionBart1 = Valoracion(puntuacion = 5, comentario = "¡Me encantó!", autor = bart)
        val valoracionBart2 = Valoracion(puntuacion = 4, comentario = "Divertido.", autor = bart)
        val valoracionBart3 = Valoracion(puntuacion = 3, comentario = "Estuvo bien.", autor = bart)

// Agregando valoraciones a las recomendaciones de otros personajes
        recomMarge2.agregarValoracion(valoracionBart1)  // Marge valora la recomendación de Bart
        recomKrusty1.agregarValoracion(valoracionBart2)  // Krusty valora la recomendación de Bart
        recomPatty1.agregarValoracion(valoracionBart3)  // Patty valora la recomendación de Bart

// Valoraciones de Marge
        val valoracionMarge1 = Valoracion(puntuacion = 5, comentario = "Una joya literaria.", autor = marge)
        val valoracionMarge2 = Valoracion(puntuacion = 4, comentario = "Lleno de emociones.", autor = marge)
        val valoracionMarge3 = Valoracion(puntuacion = 3, comentario = "Interesante pero algo lento.", autor = marge)

// Agregando valoraciones a las recomendaciones de otros personajes
        recomSelma2.agregarValoracion(valoracionMarge1)  // Selma valora la recomendación de Marge
        recomNelson1.agregarValoracion(valoracionMarge2)  // Nelson valora la recomendación de Marge
        recomMilhouse1.agregarValoracion(valoracionMarge3)  // Milhouse valora la recomendación de Marge

// Valoraciones de Selma
        val valoracionSelma1 = Valoracion(puntuacion = 5, comentario = "Reflexiones profundas.", autor = selma)
        val valoracionSelma2 = Valoracion(puntuacion = 4, comentario = "Un buen libro.", autor = selma)
        val valoracionSelma3 = Valoracion(puntuacion = 3, comentario = "Me gustó, pero hay mejores.", autor = selma)

// Agregando valoraciones a las recomendaciones de otros personajes
        recomPatty2.agregarValoracion(valoracionSelma1)  // Patty valora la recomendación de Selma
        recomBarney2.agregarValoracion(valoracionSelma2)  // Barney valora la recomendación de Selma
        recomKrusty2.agregarValoracion(valoracionSelma3)  // Krusty valora la recomendación de Selma

// Valoraciones de Patty
        val valoracionPatty1 = Valoracion(puntuacion = 5, comentario = "Increíble lectura.", autor = patty)
        val valoracionPatty2 = Valoracion(puntuacion = 4, comentario = "Me encantó.", autor = patty)
        val valoracionPatty3 = Valoracion(puntuacion = 3, comentario = "No está mal.", autor = patty)

// Agregando valoraciones a las recomendaciones de otros personajes
        recomMilhouse2.agregarValoracion(valoracionPatty1)  // Milhouse valora la recomendación de Patty
        recomBart3.agregarValoracion(valoracionPatty2)  // Bart valora la recomendación de Patty
        recomNelson2.agregarValoracion(valoracionPatty3)  // Nelson valora la recomendación de Patty

// Valoraciones de Milhouse
        val valoracionMilhouse1 = Valoracion(puntuacion = 5, comentario = "Espectacular.", autor = milhouse)
        val valoracionMilhouse2 = Valoracion(puntuacion = 4, comentario = "Muy divertido.", autor = milhouse)
        val valoracionMilhouse3 = Valoracion(puntuacion = 3, comentario = "Aceptable.", autor = milhouse)

// Agregando valoraciones a las recomendaciones de otros personajes
        recomNelson3.agregarValoracion(valoracionMilhouse1)  // Nelson valora la recomendación de Milhouse
        recomBarney3.agregarValoracion(valoracionMilhouse2)  // Barney valora la recomendación de Milhouse
        recomKrusty3.agregarValoracion(valoracionMilhouse3)  // Krusty valora la recomendación de Milhouse

// Valoraciones de Barney
        val valoracionBarney1 = Valoracion(puntuacion = 5, comentario = "¡Increíble!", autor = barney)
        val valoracionBarney2 = Valoracion(puntuacion = 4, comentario = "Un gran libro.", autor = barney)
        val valoracionBarney3 = Valoracion(puntuacion = 3, comentario = "Interesante.", autor = barney)

// Agregando valoraciones a las recomendaciones de otros personajes
        recomSkinner1.agregarValoracion(valoracionBarney1)  // Skinner valora la recomendación de Barney
        recomRalph1.agregarValoracion(valoracionBarney2)  // Ralph valora la recomendación de Barney
        recomSelma3.agregarValoracion(valoracionBarney3)  // Selma valora la recomendación de Barney

// Valoraciones de Nelson
        val valoracionNelson1 = Valoracion(puntuacion = 5, comentario = "¡Fantástico!", autor = nelson)
        val valoracionNelson2 = Valoracion(puntuacion = 4, comentario = "Me gustó mucho.", autor = nelson)
        val valoracionNelson3 = Valoracion(puntuacion = 3, comentario = "Estuvo bien.", autor = nelson)

// Agregando valoraciones a las recomendaciones de otros personajes
        recomKrusty1.agregarValoracion(valoracionNelson1)  // Krusty valora la recomendación de Nelson
        recomSkinner2.agregarValoracion(valoracionNelson2)  // Skinner valora la recomendación de Nelson
        recomLisa3.agregarValoracion(valoracionNelson3)  // Lisa valora la recomendación de Nelson

// Valoraciones de Lenny
        val valoracionLenny1 = Valoracion(puntuacion = 5, comentario = "Un libro que todos deben leer.", autor = lenny)
        val valoracionLenny2 = Valoracion(puntuacion = 4, comentario = "Recomendado.", autor = lenny)
        val valoracionLenny3 = Valoracion(puntuacion = 3, comentario = "Interesante.", autor = lenny)

// Agregando valoraciones a las recomendaciones de otros personajes
        recomMarge3.agregarValoracion(valoracionLenny1)  // Marge valora la recomendación de Lenny
        recomRalph2.agregarValoracion(valoracionLenny2)  // Ralph valora la recomendación de Lenny
        recomBarney2.agregarValoracion(valoracionLenny3)  // Barney valora la recomendación de Lenny



//        repoRecom.createItem(recomCompleta)
//        repoRecom.createItem(recomCompleta2)
//        repoRecom.createItem(recomCompleta3)
//        repoRecom.createItem(recomCompleta4)
//        repoRecom.createItem(recomCompletaConValoracion)
        repoRecom.createItem(recomRalph3)
        repoRecom.createItem(recomRalph2)
        repoRecom.createItem(recomRalph1)
        repoRecom.createItem(recomHomero1)
        repoRecom.createItem(recomHomero2)
        repoRecom.createItem(recomHomero3)
        repoRecom.createItem(recomLisa1)
        repoRecom.createItem(recomLisa2)
        repoRecom.createItem(recomLisa3)
        repoRecom.createItem(recomBart1)
        repoRecom.createItem(recomBart2)
        repoRecom.createItem(recomBart3)
        repoRecom.createItem(recomMarge1)
        repoRecom.createItem(recomMarge2)
        repoRecom.createItem(recomMarge3)
        repoRecom.createItem(recomSelma1)
        repoRecom.createItem(recomSelma2)
        repoRecom.createItem(recomSelma3)
        repoRecom.createItem(recomPatty1)
        repoRecom.createItem(recomPatty2)
        repoRecom.createItem(recomPatty3)
        repoRecom.createItem(recomMilhouse1)
        repoRecom.createItem(recomMilhouse2)
        repoRecom.createItem(recomMilhouse3)
        repoRecom.createItem(recomBarney1)
        repoRecom.createItem(recomBarney2)
        repoRecom.createItem(recomBarney3)
        repoRecom.createItem(recomNelson1)
        repoRecom.createItem(recomNelson2)
        repoRecom.createItem(recomNelson3)
        repoRecom.createItem(recomLenny1)
        repoRecom.createItem(recomLenny2)
        repoRecom.createItem(recomLenny3)
        repoRecom.createItem(recomNed1)
        repoRecom.createItem(recomNed2)
        repoRecom.createItem(recomNed3)
        repoRecom.createItem(recomKrusty1)
        repoRecom.createItem(recomKrusty2)
        repoRecom.createItem(recomKrusty3)
        repoRecom.createItem(recomSkinner1)
        repoRecom.createItem(recomSkinner2)
        repoRecom.createItem(recomSkinner3)
    }
}