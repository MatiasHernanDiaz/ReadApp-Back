package ar.edu.unsam.algo3

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class LibroSpec : DescribeSpec({
    describe("Desafiante/LecturaCompleja") {
        it("Best seller con muchas ventas y lenguajes") {
            val miguel = Autor(nombre = "Miguel", apellido = "de Cervantes", seudonimo = "El manco", idiomaNativo = Lenguaje.ESPANIOL,fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf())
            val donQuijote = Libro(
                titulo = "Don Quijote de la mancha",
                autor = miguel,
                imageURL = "",
                paginas = 300,
                palabras = 50000,
                ediciones = 1,
                ventasSemanales = 10001,
                lecturaCompleja = true,
                traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ALEMAN, Lenguaje.PORTUGUES, Lenguaje.RUSO, Lenguaje.ITALIANO, Lenguaje.FRANCES)
            )
            donQuijote.esDesafiante() shouldBe true
            donQuijote.esBestSeller() shouldBe true
        }
        it("Best seller con muchas ventas y ediciones") {
            val miyamoto = Autor(nombre = "shigeru", apellido = "miyamoto", seudonimo = "Ponja", idiomaNativo = Lenguaje.JAPONES,fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf())
            val zelda = Libro(
                titulo = "The legend of Zelda: ocarina of time. the book",
                autor = miyamoto,
                imageURL = "",
                paginas = 200,
                palabras = 15000,
                ediciones = 3,
                ventasSemanales = 10001,
                lecturaCompleja = true,
                traducciones = mutableSetOf()
            )
            zelda.esDesafiante() shouldBe true
            zelda.esBestSeller() shouldBe true
        }
        it("Fracazo con muchas ventas") {
            val mama = Autor(nombre = "Mama", apellido = "Fresh", seudonimo = "Mamerto", idiomaNativo = Lenguaje.ESPANIOL,fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf())
            val guiaCocina = Libro(
                titulo = "aprende a cocinar con Mama Fresh",
                autor = mama,
                imageURL = "",
                paginas = 300,
                palabras = 10000,
                ediciones = 1,
                ventasSemanales = 10001,
                lecturaCompleja = true,
                traducciones = mutableSetOf()
            )
            guiaCocina.esDesafiante() shouldBe true
            guiaCocina.esBestSeller() shouldBe false
        }
        it("fracazo con pocas ventas y muchas ediciones") {
            val araki = Autor(nombre = "Hirohiko", apellido = "Araki", seudonimo = "Harakiri", idiomaNativo = Lenguaje.JAPONES,fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf())
            val jojos = Libro(
                titulo = "jojos bizzare adventure: phantom blood cap 1",
                autor = araki,
                imageURL = "",
                paginas = 100,
                palabras = 5000,
                ediciones = 3,
                ventasSemanales = 9000,
                lecturaCompleja = true,
                traducciones = mutableSetOf()
            )
            jojos.esDesafiante() shouldBe true
            jojos.esBestSeller() shouldBe false
        }
        it("fracazo con pocas ventas y muchos idiomas") {
            val matel = Autor(nombre = "editorial Matel", apellido = "S/A", seudonimo = "", idiomaNativo = Lenguaje.ESPANIOL,fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf())
            val historia = Libro(
                titulo = "Libro de historia para cuarto grado",
                autor = matel,
                imageURL = "",
                paginas = 500,
                palabras = 70000,
                ediciones = 1,
                ventasSemanales = 9000,
                lecturaCompleja = true,
                traducciones = mutableSetOf( Lenguaje.INGLES, Lenguaje.ALEMAN, Lenguaje.PORTUGUES, Lenguaje.RUSO, Lenguaje.HINDI,Lenguaje.ARABE)
            )
            historia.esDesafiante() shouldBe true
            historia.esBestSeller() shouldBe false
        }
    }
    describe("Es desafiante con muchas paginas") {
        val german = Autor(nombre = "Héctor Germán", apellido = "Oesterheld", seudonimo = "Eter", idiomaNativo = Lenguaje.ESPANIOL,fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf())
        it(" BestSeller Muchas ventas,Muchas ediciones y poco lenguajes") {
            val elEternauta = Libro(
                titulo = "el Eternauta PARTE 1",
                autor = german,
                imageURL = "",
                paginas = 1000,
                ediciones = 20,
                palabras = 10000,
                ventasSemanales = 100000,
                lecturaCompleja = false,
                traducciones = mutableSetOf(Lenguaje.INGLES)
            )
            elEternauta.esDesafiante() shouldBe true
            elEternauta.esBestSeller() shouldBe true
        }
        it(" BestSeller Muchas ventas,Pocas ediciones y Muchos lenguajes") {
            val marquez = Autor(nombre = "Gabriel", apellido = "García Márquez", seudonimo = "Gabo", idiomaNativo = Lenguaje.ESPANIOL,fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf())
            val cronicasDeUnaMuerte = Libro(
                titulo = "Cronicas de una muerte anunciada",
                autor = marquez,
                imageURL = "",
                paginas = 1000,
                ediciones = 2,
                palabras = 10000,
                ventasSemanales = 100000,
                lecturaCompleja = false,
                traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.ALEMAN, Lenguaje.PORTUGUES, Lenguaje.RUSO, Lenguaje.BENGALI)
            )
            cronicasDeUnaMuerte.esDesafiante() shouldBe true
            cronicasDeUnaMuerte.esBestSeller() shouldBe true
        }
        it(" No bestSeller pocas ediciones,Pocos lenguajes") {
            val zenon = Autor(nombre = "mister", apellido = "zenon", seudonimo = "El gaucho", idiomaNativo = Lenguaje.RUSO,fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf())
            val laGranjaDeZenon = Libro(
                titulo = "la granja de zenon",
                autor = zenon,
                imageURL = "",
                paginas = 1000,
                ediciones = 2,
                palabras = 10000,
                ventasSemanales = 1000,
                lecturaCompleja = false,
                traducciones = mutableSetOf(Lenguaje.MANDARIN)
            )
            laGranjaDeZenon.esDesafiante() shouldBe true
            laGranjaDeZenon.esBestSeller() shouldBe false
        }
        it(" No bestSeller muchas ediciones,Pocas lenguajes") {
            val vasconcelos = Autor(nombre = "José", apellido = "de Vasconcelos", seudonimo = "brazuca", idiomaNativo = Lenguaje.RUSO,fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf())
            val elPalacioJapones = Libro(
                titulo = "El palacio japones",
                autor = vasconcelos,
                imageURL = "",
                paginas = 1000,
                ediciones = 200,
                palabras = 10000,
                ventasSemanales = 100,
                lecturaCompleja = false,
                traducciones = mutableSetOf(Lenguaje.HINDI)
            )
            elPalacioJapones.esDesafiante() shouldBe true
            elPalacioJapones.esBestSeller() shouldBe false
        }

    }

    describe("No es desafiante") {
        //faltan Best Seller y No Best Seller
        it("Es corto y no es de lectura compleja") {
            val perrault = Autor(nombre = "Charles", apellido = "Perrault", seudonimo = "Caperucito", idiomaNativo = Lenguaje.FRANCES,fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf())
            val caperucita = Libro(
                titulo = "La Caperucita Roja",
                autor = perrault,
                imageURL = "",
                paginas = 599,
                ediciones = 1,
                palabras = 10000,
                ventasSemanales = 10000,
                lecturaCompleja = false,
                traducciones = mutableSetOf()
            )

            caperucita.esDesafiante() shouldBe false
        }

    }

    describe("Autor y Traducciones"){
        val perrault = Autor(nombre = "Charles", apellido = "Perrault", seudonimo = "Caperucito", idiomaNativo = Lenguaje.FRANCES,fechaNacimiento = LocalDate.of(1968, 6, 9), premios= mutableListOf())
        it("Idioma original") {
            val pulgarcito = Libro(
                titulo = "Pulgarcito",
                autor = perrault,
                imageURL = "",
                paginas = 599,
                ediciones = 1,
                palabras = 10000,
                ventasSemanales = 10000,
                lecturaCompleja = false,
                traducciones = mutableSetOf()
            )

            pulgarcito.idiomaOriginal() shouldBe Lenguaje.FRANCES
        }
        it("No tiene traducciones") {
            val pulgarcito = Libro(
                titulo = "Pulgarcito",
                autor = perrault,
                imageURL = "",
                paginas = 599,
                ediciones = 1,
                palabras = 10000,
                ventasSemanales = 10000,
                lecturaCompleja = false,
                traducciones = mutableSetOf()
            )

            pulgarcito.traducciones() shouldBe listOf()
        }
        it("Muchas traducciones"){
            val cenicienta = Libro(
                titulo = "Cenicienta",
                autor = perrault,
                imageURL = "",
                paginas = 300,
                ediciones = 5,
                palabras = 30000,
                ventasSemanales = 200000,
                lecturaCompleja = false,
                traducciones = mutableSetOf(Lenguaje.ESPANIOL,Lenguaje.ALEMAN,Lenguaje.JAPONES)
            )

            cenicienta.traducciones() shouldBe listOf(Lenguaje.ESPANIOL,Lenguaje.ALEMAN,Lenguaje.JAPONES)
        }
    }
})
