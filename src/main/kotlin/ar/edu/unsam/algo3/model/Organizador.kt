package ar.edu.unsam.algo3

interface Organizador {
    fun costoReserva(centroLectura: CentroLectura): Float
    fun cantMaxParticipantes(): Int
    fun costoBase() = 1000F

}

class Particular(private val cantidadMaxima: Int, private val porcentajeAdicional: Float) : Organizador {
    override fun cantMaxParticipantes(): Int {
        return cantidadMaxima
    }

    override fun costoReserva(centroLectura: CentroLectura): Float {
        if (centroLectura.listaDeUsuarioConReserva.size > (cantidadMaxima *porcentajeAdicional)) {
            return costoBase() + 500
        }
        return costoBase()
    }

}

class Editorial(
    private val autorPresente: Boolean,
    private val montoAlcanzar: Float
) : Organizador {

    override fun costoBase(): Float = super.costoBase() + 800F

    override fun cantMaxParticipantes(): Int {
        return (montoAlcanzar / costoBase()).toInt()
    }

    override fun costoReserva(centroLectura: CentroLectura): Float {
        if (autorPresente) {
            if (centroLectura.libro.esBestSeller()) {
                return centroLectura.libro.getVentasSemanales() * 0.1F + costoBase()
            } else
                return  costoBase()+200F
        }
        return costoBase()
    }
}




class Biblioteca(
    private val listaDeGastosFijos: List<Float>,
    private val metrosCuadrados: Int,
) : Organizador {

    override fun cantMaxParticipantes(): Int {
        return metrosCuadrados
    }

    fun gastosFijos() = listaDeGastosFijos.sum()
    override fun costoReserva(centroLectura: CentroLectura): Float {
        var cantidadDeDias = centroLectura.fechas.size

        val margen = if (cantidadDeDias < 5) {
            0.1F * cantidadDeDias
        } else {
            0.5F
        }
        return (gastosFijos() * (1 + margen)) / centroLectura.listaDeUsuarioConReserva.size


    }
}