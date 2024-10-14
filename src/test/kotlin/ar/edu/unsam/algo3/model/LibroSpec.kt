package ar.edu.unsam.algo3

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class LibroSpec : DescribeSpec({
    describe("Desafiante/LecturaCompleja") {
        it("Best seller con muchas ventas y lenguajes") {
            val miguel = Author(firstName = "Miguel", lastName = "de Cervantes", alias = "El manco", nativeLanguage = Language.SPANISH,birthday = LocalDate.of(1968, 6, 9), prices= mutableListOf())
            val donQuijote = Libro(
                titulo = "Don Quijote de la mancha",
                autor = miguel,
                paginas = 300,
                palabras = 50000,
                ediciones = 1,
                ventasSemanales = 10001,
                lecturaCompleja = true,
                traducciones = mutableSetOf(Language.ENGLISH, Language.GERMAN, Language.PORTUGUESE, Language.RUSSIAN, Language.ITALIAN, Language.FRENCH),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )
            donQuijote.esDesafiante() shouldBe true
            donQuijote.esBestSeller() shouldBe true
        }
        it("Best seller con muchas ventas y ediciones") {
            val miyamoto = Author(firstName = "shigeru", lastName = "miyamoto", alias = "Ponja", nativeLanguage = Language.JAPANESE,birthday = LocalDate.of(1968, 6, 9), prices= mutableListOf())
            val zelda = Libro(
                titulo = "The legend of Zelda: ocarina of time. the book",
                autor = miyamoto,
                paginas = 200,
                palabras = 15000,
                ediciones = 3,
                ventasSemanales = 10001,
                lecturaCompleja = true,
                traducciones = mutableSetOf(),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )
            zelda.esDesafiante() shouldBe true
            zelda.esBestSeller() shouldBe true
        }
        it("Fracazo con muchas ventas") {
            val mama = Author(firstName = "Mama", lastName = "Fresh", alias = "Mamerto", nativeLanguage = Language.SPANISH,birthday = LocalDate.of(1968, 6, 9), prices= mutableListOf())
            val guiaCocina = Libro(
                titulo = "aprende a cocinar con Mama Fresh",
                autor = mama,
                paginas = 300,
                palabras = 10000,
                ediciones = 1,
                ventasSemanales = 10001,
                lecturaCompleja = true,
                traducciones = mutableSetOf(),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )
            guiaCocina.esDesafiante() shouldBe true
            guiaCocina.esBestSeller() shouldBe false
        }
        it("fracazo con pocas ventas y muchas ediciones") {
            val araki = Author(firstName = "Hirohiko", lastName = "Araki", alias = "Harakiri", nativeLanguage = Language.JAPANESE,birthday = LocalDate.of(1968, 6, 9), prices= mutableListOf())
            val jojos = Libro(
                titulo = "jojos bizzare adventure: phantom blood cap 1",
                autor = araki,
                paginas = 100,
                palabras = 5000,
                ediciones = 3,
                ventasSemanales = 9000,
                lecturaCompleja = true,
                traducciones = mutableSetOf(),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )
            jojos.esDesafiante() shouldBe true
            jojos.esBestSeller() shouldBe false
        }
        it("fracazo con pocas ventas y muchos idiomas") {
            val matel = Author(firstName = "editorial Matel", lastName = "S/A", alias = "", nativeLanguage = Language.SPANISH,birthday = LocalDate.of(1968, 6, 9), prices= mutableListOf())
            val historia = Libro(
                titulo = "Libro de historia para cuarto grado",
                autor = matel,
                paginas = 500,
                palabras = 70000,
                ediciones = 1,
                ventasSemanales = 9000,
                lecturaCompleja = true,
                traducciones = mutableSetOf( Language.ENGLISH, Language.GERMAN, Language.PORTUGUESE, Language.RUSSIAN, Language.HINDI,Language.ARAB),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )
            historia.esDesafiante() shouldBe true
            historia.esBestSeller() shouldBe false
        }
    }
    describe("Es desafiante con muchas paginas") {
        val german = Author(firstName = "Héctor Germán", lastName = "Oesterheld", alias = "Eter", nativeLanguage = Language.SPANISH,birthday = LocalDate.of(1968, 6, 9), prices= mutableListOf())
        it(" BestSeller Muchas ventas,Muchas ediciones y poco lenguajes") {
            val elEternauta = Libro(
                titulo = "el Eternauta PARTE 1",
                autor = german,
                paginas = 1000,
                ediciones = 20,
                palabras = 10000,
                ventasSemanales = 100000,
                lecturaCompleja = false,
                traducciones = mutableSetOf(Language.ENGLISH),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )
            elEternauta.esDesafiante() shouldBe true
            elEternauta.esBestSeller() shouldBe true
        }
        it(" BestSeller Muchas ventas,Pocas ediciones y Muchos lenguajes") {
            val marquez = Author(firstName = "Gabriel", lastName = "García Márquez", alias = "Gabo", nativeLanguage = Language.SPANISH,birthday = LocalDate.of(1968, 6, 9), prices= mutableListOf())
            val cronicasDeUnaMuerte = Libro(
                titulo = "Cronicas de una muerte anunciada",
                autor = marquez,
                paginas = 1000,
                ediciones = 2,
                palabras = 10000,
                ventasSemanales = 100000,
                lecturaCompleja = false,
                traducciones = mutableSetOf(Language.ENGLISH, Language.GERMAN, Language.PORTUGUESE, Language.RUSSIAN, Language.BENGALI),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )
            cronicasDeUnaMuerte.esDesafiante() shouldBe true
            cronicasDeUnaMuerte.esBestSeller() shouldBe true
        }
        it(" No bestSeller pocas ediciones,Pocos lenguajes") {
            val zenon = Author(firstName = "mister", lastName = "zenon", alias = "El gaucho", nativeLanguage = Language.RUSSIAN,birthday = LocalDate.of(1968, 6, 9), prices= mutableListOf())
            val laGranjaDeZenon = Libro(
                titulo = "la granja de zenon",
                autor = zenon,
                paginas = 1000,
                ediciones = 2,
                palabras = 10000,
                ventasSemanales = 1000,
                lecturaCompleja = false,
                traducciones = mutableSetOf(Language.MANDARIN),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )
            laGranjaDeZenon.esDesafiante() shouldBe true
            laGranjaDeZenon.esBestSeller() shouldBe false
        }
        it(" No bestSeller muchas ediciones,Pocas lenguajes") {
            val vasconcelos = Author(firstName = "José", lastName = "de Vasconcelos", alias = "brazuca", nativeLanguage = Language.RUSSIAN,birthday = LocalDate.of(1968, 6, 9), prices= mutableListOf())
            val elPalacioJapones = Libro(
                titulo = "El palacio japones",
                autor = vasconcelos,
                paginas = 1000,
                ediciones = 200,
                palabras = 10000,
                ventasSemanales = 100,
                lecturaCompleja = false,
                traducciones = mutableSetOf(Language.HINDI),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )
            elPalacioJapones.esDesafiante() shouldBe true
            elPalacioJapones.esBestSeller() shouldBe false
        }

    }

    describe("No es desafiante") {
        //faltan Best Seller y No Best Seller
        it("Es corto y no es de lectura compleja") {
            val perrault = Author(firstName = "Charles", lastName = "Perrault", alias = "Caperucito", nativeLanguage = Language.FRENCH,birthday = LocalDate.of(1968, 6, 9), prices= mutableListOf())
            val caperucita = Libro(
                titulo = "La Caperucita Roja",
                autor = perrault,
                paginas = 599,
                ediciones = 1,
                palabras = 10000,
                ventasSemanales = 10000,
                lecturaCompleja = false,
                traducciones = mutableSetOf(),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )

            caperucita.esDesafiante() shouldBe false
        }

    }

    describe("Autor y Traducciones"){
        val perrault = Author(firstName = "Charles", lastName = "Perrault", alias = "Caperucito", nativeLanguage = Language.FRENCH,birthday = LocalDate.of(1968, 6, 9), prices= mutableListOf())
        it("Idioma original") {
            val pulgarcito = Libro(
                titulo = "Pulgarcito",
                autor = perrault,
                paginas = 599,
                ediciones = 1,
                palabras = 10000,
                ventasSemanales = 10000,
                lecturaCompleja = false,
                traducciones = mutableSetOf(),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )

            pulgarcito.idiomaOriginal() shouldBe Language.FRENCH
        }
        it("No tiene traducciones") {
            val pulgarcito = Libro(
                titulo = "Pulgarcito",
                autor = perrault,
                paginas = 599,
                ediciones = 1,
                palabras = 10000,
                ventasSemanales = 10000,
                lecturaCompleja = false,
                traducciones = mutableSetOf(),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )

            pulgarcito.traducciones() shouldBe listOf()
        }
        it("Muchas traducciones"){
            val cenicienta = Libro(
                titulo = "Cenicienta",
                autor = perrault,
                paginas = 300,
                ediciones = 5,
                palabras = 30000,
                ventasSemanales = 200000,
                lecturaCompleja = false,
                traducciones = mutableSetOf(Language.SPANISH,Language.GERMAN,Language.JAPANESE),
                fecha = 1,
                imagenURL = "ruta/a/la/imagen"
            )

            cenicienta.traducciones() shouldBe listOf(Language.SPANISH,Language.GERMAN,Language.JAPANESE)
        }
    }
})
