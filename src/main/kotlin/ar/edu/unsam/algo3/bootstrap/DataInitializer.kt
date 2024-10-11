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

        val marquez = Autor(
            nombre = "Gabriel",
            apellido = "García Márquez",
            seudonimo = "Gabo",
            idiomaNativo = Lenguaje.ESPANIOL,
            fechaNacimiento = LocalDate.of(1927, 3, 6)
        )

        val cervantes = Autor(
            nombre = "Miguel",
            apellido = "de Cervantes",
            seudonimo = "",
            idiomaNativo = Lenguaje.ESPANIOL,
            fechaNacimiento = LocalDate.of(1547, 9, 29)
        )

        val shakespeare = Autor(
            nombre = "William",
            apellido = "Shakespeare",
            seudonimo = "",
            idiomaNativo = Lenguaje.INGLES,
            fechaNacimiento = LocalDate.of(1564, 4, 26)
        )

        val tolstoy = Autor(
            nombre = "Leo",
            apellido = "Tolstoy",
            seudonimo = "",
            idiomaNativo = Lenguaje.RUSO,
            fechaNacimiento = LocalDate.of(1828, 9, 9)
        )

        val austen = Autor(
            nombre = "Jane",
            apellido = "Austen",
            seudonimo = "",
            idiomaNativo = Lenguaje.INGLES,
            fechaNacimiento = LocalDate.of(1775, 12, 16)
        )

        val kafka = Autor(
            nombre = "Franz",
            apellido = "Kafka",
            seudonimo = "",
            idiomaNativo = Lenguaje.ALEMAN,
            fechaNacimiento = LocalDate.of(1883, 7, 3)
        )

        val woolf = Autor(
            nombre = "Virginia",
            apellido = "Woolf",
            seudonimo = "",
            idiomaNativo = Lenguaje.INGLES,
            fechaNacimiento = LocalDate.of(1882, 1, 25)
        )

        val dostoevsky = Autor(
            nombre = "Fyodor",
            apellido = "Dostoevsky",
            seudonimo = "",
            idiomaNativo = Lenguaje.RUSO,
            fechaNacimiento = LocalDate.of(1821, 11, 11)
        )

        val orwell = Autor(
            nombre = "George",
            apellido = "Orwell",
            seudonimo = "Eric Arthur Blair",
            idiomaNativo = Lenguaje.INGLES,
            fechaNacimiento = LocalDate.of(1903, 6, 25)
        )

        repoAutor.crearItem(borges)
        repoAutor.crearItem(cortazar)
        repoAutor.crearItem(stamateas)
        repoAutor.crearItem(hirsch)
        repoAutor.crearItem(toriyama)
        repoAutor.crearItem(Gombrich)
        repoAutor.crearItem(nik)
        repoAutor.crearItem(lovecraft)
        repoAutor.crearItem(marquez)
        repoAutor.crearItem(orwell)
        repoAutor.crearItem(dostoevsky)
        repoAutor.crearItem(woolf)
        repoAutor.crearItem(kafka)
        repoAutor.crearItem(austen)
        repoAutor.crearItem(tolstoy)
        repoAutor.crearItem(shakespeare)
        repoAutor.crearItem(cervantes)

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

// Borges

        val ficciones = Libro(
            titulo = "Ficciones",
            autor = borges,
            paginas = 200,
            palabras = 45_000,
            ediciones = 6,
            ventasSemanales = 150,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.INGLES)
        )


        val elInformeDeBrodie = Libro(
            titulo = "El informe de Brodie",
            autor = borges,
            paginas = 160,
            palabras = 38_000,
            ediciones = 3,
            ventasSemanales = 100,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.FRANCES)
        )

        val elLibroDeArena = Libro(
            titulo = "El libro de arena",
            autor = borges,
            paginas = 190,
            palabras = 42_000,
            ediciones = 5,
            ventasSemanales = 110,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.INGLES, Lenguaje.ALEMAN)
        )

        val historiaUniversalDeLaInfamia = Libro(
            titulo = "Historia universal de la infamia",
            autor = borges,
            paginas = 150,
            palabras = 37_000,
            ediciones = 2,
            ventasSemanales = 90,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.PORTUGUES)
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
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.INGLES, Lenguaje.FRANCES)
        )


        val elAmorEnLosTiemposDelColera = Libro(
            titulo = "El amor en los tiempos del cólera",
            autor = marquez,
            paginas = 420,
            palabras = 120_000,
            ediciones = 8,
            ventasSemanales = 350,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.ITALIANO)
        )

        val cronicaDeUnaMuerteAnunciada = Libro(
            titulo = "Crónica de una muerte anunciada",
            autor = marquez,
            paginas = 180,
            palabras = 50_000,
            ediciones = 5,
            ventasSemanales = 180,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.INGLES)
        )


        val elOtonoDelPatriarca = Libro(
            titulo = "El otoño del patriarca",
            autor = marquez,
            paginas = 300,
            palabras = 80_000,
            ediciones = 4,
            ventasSemanales = 200,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.PORTUGUES)
        )


        val laHojarasca = Libro(
            titulo = "La hojarasca",
            autor = marquez,
            paginas = 220,
            palabras = 70_000,
            ediciones = 3,
            ventasSemanales = 150,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL)
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
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.INGLES, Lenguaje.FRANCES)
        )


        val lasNovelasEjemplares = Libro(
            titulo = "Las novelas ejemplares",
            autor = cervantes,
            paginas = 400,
            palabras = 120_000,
            ediciones = 8,
            ventasSemanales = 250,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.ITALIANO)
        )


        val losTrabajosDePersiles = Libro(
            titulo = "Los trabajos de Persiles y Sigismunda",
            autor = cervantes,
            paginas = 600,
            palabras = 200_000,
            ediciones = 7,
            ventasSemanales = 300,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.ALEMAN)
        )

        val laGalatea = Libro(
            titulo = "La Galatea",
            autor = cervantes,
            paginas = 350,
            palabras = 100_000,
            ediciones = 4,
            ventasSemanales = 180,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL, Lenguaje.PORTUGUES)
        )

        val elViajeDelParnaso = Libro(
            titulo = "El viaje del Parnaso",
            autor = cervantes,
            paginas = 280,
            palabras = 85_000,
            ediciones = 3,
            ventasSemanales = 120,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ESPANIOL)
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
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ESPANIOL)
        )


        val macbeth = Libro(
            titulo = "Macbeth",
            autor = shakespeare,
            paginas = 240,
            palabras = 80_000,
            ediciones = 8,
            ventasSemanales = 400,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ITALIANO)
        )


        val othello = Libro(
            titulo = "Othello",
            autor = shakespeare,
            paginas = 280,
            palabras = 85_000,
            ediciones = 7,
            ventasSemanales = 350,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES)
        )

        val romeoYJulieta = Libro(
            titulo = "Romeo y Julieta",
            autor = shakespeare,
            paginas = 260,
            palabras = 78_000,
            ediciones = 6,
            ventasSemanales = 300,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ESPANIOL)
        )

        val julioCesar = Libro(
            titulo = "Julio César",
            autor = shakespeare,
            paginas = 250,
            palabras = 75_000,
            ediciones = 5,
            ventasSemanales = 250,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ALEMAN)
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
            traducciones = mutableSetOf(Lenguaje.RUSO, Lenguaje.INGLES, Lenguaje.FRANCES)
        )

        val annaKarenina = Libro(
            titulo = "Anna Karenina",
            autor = tolstoy,
            paginas = 864,
            palabras = 340_000,
            ediciones = 10,
            ventasSemanales = 750,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.RUSO, Lenguaje.INGLES)
        )

        val resurreccion = Libro(
            titulo = "Resurrección",
            autor = tolstoy,
            paginas = 600,
            palabras = 260_000,
            ediciones = 8,
            ventasSemanales = 500,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.RUSO, Lenguaje.ITALIANO)
        )

        val laMuerteDeIvanIlich = Libro(
            titulo = "La muerte de Iván Ilich",
            autor = tolstoy,
            paginas = 140,
            palabras = 50_000,
            ediciones = 5,
            ventasSemanales = 300,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.RUSO, Lenguaje.ESPANIOL)
        )

        val hadjiMurad = Libro(
            titulo = "Hadji Murad",
            autor = tolstoy,
            paginas = 240,
            palabras = 80_000,
            ediciones = 4,
            ventasSemanales = 250,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.RUSO, Lenguaje.INGLES)
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
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ESPANIOL)
        )

        val sentidoYSensibilidad = Libro(
            titulo = "Sentido y sensibilidad",
            autor = austen,
            paginas = 368,
            palabras = 100_000,
            ediciones = 8,
            ventasSemanales = 500,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ITALIANO)
        )

        val emma = Libro(
            titulo = "Emma",
            autor = austen,
            paginas = 512,
            palabras = 150_000,
            ediciones = 9,
            ventasSemanales = 550,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES)
        )

        val mansfieldPark = Libro(
            titulo = "Mansfield Park",
            autor = austen,
            paginas = 400,
            palabras = 130_000,
            ediciones = 6,
            ventasSemanales = 400,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ESPANIOL)
        )

        val persuasion = Libro(
            titulo = "Persuasión",
            autor = austen,
            paginas = 288,
            palabras = 90_000,
            ediciones = 7,
            ventasSemanales = 350,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.PORTUGUES)
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
            traducciones = mutableSetOf(Lenguaje.ALEMAN, Lenguaje.INGLES)
        )

        val elProceso = Libro(
            titulo = "El proceso",
            autor = kafka,
            paginas = 320,
            palabras = 85_000,
            ediciones = 8,
            ventasSemanales = 350,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ALEMAN, Lenguaje.ESPANIOL)
        )

        val elCastillo = Libro(
            titulo = "El castillo",
            autor = kafka,
            paginas = 400,
            palabras = 100_000,
            ediciones = 7,
            ventasSemanales = 320,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.ALEMAN, Lenguaje.FRANCES)
        )

        val cartasAlPadre = Libro(
            titulo = "Cartas al padre",
            autor = kafka,
            paginas = 240,
            palabras = 60_000,
            ediciones = 4,
            ventasSemanales = 280,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.ALEMAN, Lenguaje.INGLES)
        )

        val enLaColoniaPenal = Libro(
            titulo = "En la colonia penal",
            autor = kafka,
            paginas = 140,
            palabras = 35_000,
            ediciones = 3,
            ventasSemanales = 200,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.ALEMAN, Lenguaje.ESPANIOL)
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
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ESPANIOL)
        )

        val alFaro = Libro(
            titulo = "Al faro",
            autor = woolf,
            paginas = 209,
            palabras = 65_000,
            ediciones = 5,
            ventasSemanales = 350,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES)
        )

        val lasOlas = Libro(
            titulo = "Las olas",
            autor = woolf,
            paginas = 297,
            palabras = 70_000,
            ediciones = 7,
            ventasSemanales = 380,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ITALIANO)
        )

        val orlando = Libro(
            titulo = "Orlando",
            autor = woolf,
            paginas = 336,
            palabras = 80_000,
            ediciones = 6,
            ventasSemanales = 300,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ESPANIOL)
        )

        val unaHabitacionPropia = Libro(
            titulo = "Una habitación propia",
            autor = woolf,
            paginas = 172,
            palabras = 40_000,
            ediciones = 4,
            ventasSemanales = 260,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ESPANIOL)
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
            traducciones = mutableSetOf(Lenguaje.RUSO, Lenguaje.INGLES, Lenguaje.ESPANIOL)
        )

        val elIdiota = Libro(
            titulo = "El idiota",
            autor = dostoevsky,
            paginas = 656,
            palabras = 170_000,
            ediciones = 8,
            ventasSemanales = 550,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.RUSO, Lenguaje.ITALIANO)
        )

        val losHermanosKaramazov = Libro(
            titulo = "Los hermanos Karamazov",
            autor = dostoevsky,
            paginas = 824,
            palabras = 200_000,
            ediciones = 10,
            ventasSemanales = 600,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.RUSO, Lenguaje.FRANCES)
        )

        val demonios = Libro(
            titulo = "Demonios",
            autor = dostoevsky,
            paginas = 768,
            palabras = 190_000,
            ediciones = 7,
            ventasSemanales = 500,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.RUSO, Lenguaje.INGLES)
        )

        val elJugador = Libro(
            titulo = "El jugador",
            autor = dostoevsky,
            paginas = 240,
            palabras = 60_000,
            ediciones = 5,
            ventasSemanales = 350,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.RUSO, Lenguaje.ESPANIOL)
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
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ESPANIOL)
        )

        val rebelionEnLaGranja = Libro(
            titulo = "Rebelión en la granja",
            autor = orwell,
            paginas = 112,
            palabras = 30_000,
            ediciones = 10,
            ventasSemanales = 700,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES)
        )

        val homenajeACataluna = Libro(
            titulo = "Homenaje a Cataluña",
            autor = orwell,
            paginas = 232,
            palabras = 60_000,
            ediciones = 6,
            ventasSemanales = 400,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ITALIANO)
        )

        val losDiasDeBirmania = Libro(
            titulo = "Los días de Birmania",
            autor = orwell,
            paginas = 288,
            palabras = 75_000,
            ediciones = 5,
            ventasSemanales = 300,
            lecturaCompleja = true,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ESPANIOL)
        )

        val sinBlancaEnParisYLondres = Libro(
            titulo = "Sin blanca en París y Londres",
            autor = orwell,
            paginas = 232,
            palabras = 60_000,
            ediciones = 4,
            ventasSemanales = 250,
            lecturaCompleja = false,
            traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ITALIANO)
        )

        repoBook.crearItem(aleph)
        repoBook.crearItem(rayuela)
        repoBook.crearItem(genteToxica)
        repoBook.crearItem(bill)
        repoBook.crearItem(dragonBall)
        repoBook.crearItem(historiaDelArte)
        repoBook.crearItem(gaturro)
        repoBook.crearItem(cthulhu)
        repoBook.crearItem(julioCesar)
        repoBook.crearItem(romeoYJulieta)
        repoBook.crearItem(othello)
        repoBook.crearItem(macbeth)
        repoBook.crearItem(hamlet)
        repoBook.crearItem(elViajeDelParnaso)
        repoBook.crearItem(laGalatea)
        repoBook.crearItem(losTrabajosDePersiles)
        repoBook.crearItem(lasNovelasEjemplares)
        repoBook.crearItem(donQuijote)
        repoBook.crearItem(laHojarasca)
        repoBook.crearItem(elOtonoDelPatriarca)
        repoBook.crearItem(cronicaDeUnaMuerteAnunciada)
        repoBook.crearItem(cienAnosDeSoledad)
        repoBook.crearItem(elAmorEnLosTiemposDelColera)
        repoBook.crearItem(historiaUniversalDeLaInfamia)
        repoBook.crearItem(elLibroDeArena)
        repoBook.crearItem(elInformeDeBrodie)
        repoBook.crearItem(ficciones)
        repoBook.crearItem(sinBlancaEnParisYLondres)
        repoBook.crearItem(_1984)
        repoBook.crearItem(elJugador)
        repoBook.crearItem(demonios)
        repoBook.crearItem(losHermanosKaramazov)
        repoBook.crearItem(elIdiota)
        repoBook.crearItem(crimenYCastigo)
        repoBook.crearItem(unaHabitacionPropia)
        repoBook.crearItem(orlando)
        repoBook.crearItem(lasOlas)
        repoBook.crearItem(alFaro)
        repoBook.crearItem(laSenoraDalloway)
        repoBook.crearItem(enLaColoniaPenal)
        repoBook.crearItem(cartasAlPadre)
        repoBook.crearItem(elCastillo)
        repoBook.crearItem(elProceso)
        repoBook.crearItem(laMetamorfosis)
        repoBook.crearItem(persuasion)
        repoBook.crearItem(mansfieldPark)
        repoBook.crearItem(emma)
        repoBook.crearItem(sentidoYSensibilidad)
        repoBook.crearItem(orgulloYPrejuicio)
        repoBook.crearItem(hadjiMurad)
        repoBook.crearItem(laMuerteDeIvanIlich)
        repoBook.crearItem(resurreccion)
        repoBook.crearItem(guerraYPaz)
        repoBook.crearItem(annaKarenina)
        repoBook.crearItem(rebelionEnLaGranja)
        repoBook.crearItem(homenajeACataluna)
        repoBook.crearItem(losDiasDeBirmania)
//        (aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
//        othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
//        donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
//        historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
//        demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
//        enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
//        orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
//        homenajeACataluna,losDiasDeBirmania)



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
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)

        )

        val marge = Usuario(
            nombre = "Marge",
            apellido = "Simpson",
            userName = "msimpson",
            password = "mandarina",
            email = "marge@simps.com",
            fechaNacimiento = LocalDate.of(1972, 6, 1),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 250,
            amigos = mutableSetOf(homero),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

            val bart = Usuario(
            nombre = "Bart",
            apellido = "Simpson",
            userName = "bsimpson",
            password = "mandarina",
            email = "bart@simps.com",
            fechaNacimiento = LocalDate.of(1980, 2, 23),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 200,
            amigos = mutableSetOf(homero, marge),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
            )

        val lisa = Usuario(
            nombre = "Lisa",
            apellido = "Simpson",
            userName = "lsimpson",
            password = "mandarina",
            email = "lisa@simps.com",
            fechaNacimiento = LocalDate.of(1984, 9, 28),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 400,
            amigos = mutableSetOf(marge, bart),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val selma = Usuario(
            nombre = "Selma",
            apellido = "Bouvier",
            userName = "sbouvier",
            password = "mandarina",
            email = "selma@simps.com",
            fechaNacimiento = LocalDate.of(1952, 7, 2),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 250,
            amigos = mutableSetOf(homero, marge),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val patty = Usuario(
            nombre = "Patty",
            apellido = "Bouvier",
            userName = "pbouvier",
            password = "mandarina",
            email = "patty@simps.com",
            fechaNacimiento = LocalDate.of(1952, 7, 2),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 230,
            amigos = mutableSetOf(selma, marge, homero),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val milhouse = Usuario(
            nombre = "Milhouse",
            apellido = "Van Houten",
            userName = "mvanhouten",
            password = "mandarina",
            email = "milhouse@simps.com",
            fechaNacimiento = LocalDate.of(1981, 11, 22),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 180,
            amigos = mutableSetOf(bart, lisa),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val barney = Usuario(
            nombre = "Barney",
            apellido = "Gumble",
            userName = "bgumble",
            password = "mandarina",
            email = "barney@simps.com",
            fechaNacimiento = LocalDate.of(1970, 3, 15),
            perfil = Experimentado(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 150,
            amigos = mutableSetOf(homero),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
            )

        val nelson = Usuario(
            nombre = "Nelson",
            apellido = "Muntz",
            userName = "nmuntz",
            password = "mandarina",
            email = "nelson@simps.com",
            fechaNacimiento = LocalDate.of(1983, 5, 15),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 220,
            amigos = mutableSetOf(bart, milhouse),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val carl = Usuario(
            nombre = "Carl",
            apellido = "Carlson",
            userName = "ccarlson",
            password = "mandarina",
            email = "carl@simps.com",
            fechaNacimiento = LocalDate.of(1971, 9, 12),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 280,
            amigos = mutableSetOf(barney, nelson),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val lenny = Usuario(
            nombre = "Lenny",
            apellido = "Leonard",
            userName = "lleonard",
            password = "mandarina",
            email = "lenny@simps.com",
            fechaNacimiento = LocalDate.of(1970, 2, 10),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 290,
            amigos = mutableSetOf(carl, barney, milhouse),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val ned = Usuario(
            nombre = "Ned",
            apellido = "Flanders",
            userName = "nflanders",
            password = "mandarina",
            email = "ned@simps.com",
            fechaNacimiento = LocalDate.of(1968, 5, 10),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 260,
            amigos = mutableSetOf(marge, selma, patty),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val skinner = Usuario(
            nombre = "Seymour",
            apellido = "Skinner",
            userName = "sskinner",
            password = "mandarina",
            email = "skinner@simps.com",
            fechaNacimiento = LocalDate.of(1970, 1, 20),
            perfil = Precavido(ned),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 240,
            amigos = mutableSetOf(bart, nelson, lisa),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val ralph = Usuario(
            nombre = "Ralph",
            apellido = "Wiggum",
            userName = "rwiggum",
            password = "mandarina",
            email = "ralph@simps.com",
            fechaNacimiento = LocalDate.of(1990, 4, 15),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 130,
            amigos = mutableSetOf(bart, milhouse, lisa),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val apu = Usuario(
            nombre = "Apu",
            apellido = "Nahasapeemapetilon",
            userName = "anahasapeemapetilon",
            password = "mandarina",
            email = "apu@simps.com",
            fechaNacimiento = LocalDate.of(1975, 12, 12),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.INGLES,
            palabrasXMinuto = 310,
            amigos = mutableSetOf(marge, selma, patty),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val judy = Usuario(
            nombre = "Judy",
            apellido = "Hopps",
            userName = "jhopps",
            password = "mandarina",
            email = "judy@simps.com",
            fechaNacimiento = LocalDate.of(1995, 3, 3),
            perfil = Experimentado(),
            lenguajeNativo = Lenguaje.INGLES,
            palabrasXMinuto = 300,
            amigos = mutableSetOf(lisa, bart, milhouse),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val groundskeeper = Usuario(
            nombre = "Willie",
            apellido = "MacDougal",
            userName = "wmacdougal",
            password = "mandarina",
            email = "willie@simps.com",
            fechaNacimiento = LocalDate.of(1965, 5, 18),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.INGLES,
            palabrasXMinuto = 250,
            amigos = mutableSetOf(bart, milhouse, nelson),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )

        val krusty = Usuario(
            nombre = "Krusty",
            apellido = "el Payaso",
            userName = "kpayaso",
            password = "mandarina",
            email = "krusty@simps.com",
            fechaNacimiento = LocalDate.of(1975, 8, 4),
            perfil = Poliglota(),
            lenguajeNativo = Lenguaje.ESPANIOL,
            palabrasXMinuto = 320,
            amigos = mutableSetOf(homero, bart, milhouse),
            librosLeidos = mutableListOf(aleph,rayuela,genteToxica,bill,dragonBall,historiaDelArte,gaturro,cthulhu,julioCesar,romeoYJulieta,
                othello,macbeth,hamlet,elViajeDelParnaso,laGalatea,losTrabajosDePersiles,lasNovelasEjemplares,
                donQuijote,laHojarasca,elOtonoDelPatriarca,cronicaDeUnaMuerteAnunciada,cienAnosDeSoledad,elAmorEnLosTiemposDelColera,
                historiaUniversalDeLaInfamia,elLibroDeArena,elInformeDeBrodie,ficciones,sinBlancaEnParisYLondres,_1984,elJugador,
                demonios,losHermanosKaramazov,elIdiota,crimenYCastigo,unaHabitacionPropia,orlando,lasOlas,alFaro,laSenoraDalloway,
                enLaColoniaPenal,cartasAlPadre,elCastillo,elProceso,laMetamorfosis,persuasion,mansfieldPark,emma,sentidoYSensibilidad,
                orgulloYPrejuicio,hadjiMurad,laMuerteDeIvanIlich,resurreccion,guerraYPaz,annaKarenina,rebelionEnLaGranja,
                homenajeACataluna,losDiasDeBirmania)
        )


        nelson.agregarAmigo(carl)
        carl.agregarAmigo(lenny)
        barney.agregarAmigo(carl)
        barney.agregarAmigo(nelson)
        homero.agregarAmigo(lisa)
        homero.agregarAmigo(marge)
        homero.agregarAmigo(carl)

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
        repoUser.crearItem(krusty)
        repoUser.crearItem(lenny)
        repoUser.crearItem(groundskeeper)
        repoUser.crearItem(ned)
        repoUser.crearItem(skinner)
        repoUser.crearItem(ralph)
        repoUser.crearItem(judy)
        repoUser.crearItem(apu)

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



//        repoRecom.crearItem(recomCompleta)
//        repoRecom.crearItem(recomCompleta2)
//        repoRecom.crearItem(recomCompleta3)
//        repoRecom.crearItem(recomCompleta4)
//        repoRecom.crearItem(recomCompletaConValoracion)
        repoRecom.crearItem(recomRalph3)
        repoRecom.crearItem(recomRalph2)
        repoRecom.crearItem(recomRalph1)
        repoRecom.crearItem(recomHomero1)
        repoRecom.crearItem(recomHomero2)
        repoRecom.crearItem(recomHomero3)
        repoRecom.crearItem(recomLisa1)
        repoRecom.crearItem(recomLisa2)
        repoRecom.crearItem(recomLisa3)
        repoRecom.crearItem(recomBart1)
        repoRecom.crearItem(recomBart2)
        repoRecom.crearItem(recomBart3)
        repoRecom.crearItem(recomMarge1)
        repoRecom.crearItem(recomMarge2)
        repoRecom.crearItem(recomMarge3)
        repoRecom.crearItem(recomSelma1)
        repoRecom.crearItem(recomSelma2)
        repoRecom.crearItem(recomSelma3)
        repoRecom.crearItem(recomPatty1)
        repoRecom.crearItem(recomPatty2)
        repoRecom.crearItem(recomPatty3)
        repoRecom.crearItem(recomMilhouse1)
        repoRecom.crearItem(recomMilhouse2)
        repoRecom.crearItem(recomMilhouse3)
        repoRecom.crearItem(recomBarney1)
        repoRecom.crearItem(recomBarney2)
        repoRecom.crearItem(recomBarney3)
        repoRecom.crearItem(recomNelson1)
        repoRecom.crearItem(recomNelson2)
        repoRecom.crearItem(recomNelson3)
        repoRecom.crearItem(recomLenny1)
        repoRecom.crearItem(recomLenny2)
        repoRecom.crearItem(recomLenny3)
        repoRecom.crearItem(recomNed1)
        repoRecom.crearItem(recomNed2)
        repoRecom.crearItem(recomNed3)
        repoRecom.crearItem(recomKrusty1)
        repoRecom.crearItem(recomKrusty2)
        repoRecom.crearItem(recomKrusty3)
        repoRecom.crearItem(recomSkinner1)
        repoRecom.crearItem(recomSkinner2)
        repoRecom.crearItem(recomSkinner3)

    }

}