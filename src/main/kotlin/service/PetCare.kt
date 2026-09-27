package org.example.service

import org.example.model.Box
import org.example.model.Canino
import org.example.model.EstadoBox
import org.example.model.Exotico
import org.example.model.Felino
import org.example.model.Paciente
import org.example.model.Ticket
import org.example.model.TipoDueno

class PetCare {

    val boxes = mutableListOf<Box>()

    val historial = mutableListOf<Ticket>()

    var totalRecaudado = 0.0

    init {
        for (numero in 1..10) {
            boxes.add(Box(numero))
        }
    }

    fun mostrarBoxes() {
        for (box in boxes) {
            box.mostrarEstado()
        }
    }

    fun validarCodigo(codigo: String): Boolean {
        val formato = Regex("^[A-Za-z]{2}[0-9]{2}[A-Za-z]{2}$")
        return formato.matches(codigo)
    }
    fun registrarEntrada(paciente: Paciente) {
        try {
            if (!validarCodigo(paciente.codigo)) {
                throw IllegalArgumentException(
                    "codigo de atencion invalido"
                )
            }

            for (box in boxes) {
                if (box.estado is EstadoBox.Libre) {
                    box.estado =
                        EstadoBox.EnProceso("Registrando entrada")
                    println(
                        "Registrando entrada de ${paciente.nombre} " +
                                "en Box ${box.numero}"
                    )
                    println("Esperando confirmacion del sensor...")

                    box.estado =
                        EstadoBox.EnAtencion(paciente)
                    println(
                        "${paciente.nombre} ingreso correctamente " +
                                "al Box ${box.numero}"
                    )
                    return
                }
            }

            println("AVISO: no hay boxes libres disponibles")
        } catch (error: IllegalArgumentException) {
            println("AVISO: ${error.message}")
        }
    }

    fun calcularMontoFinal(
        paciente: Paciente,
        minutos: Int
    ): Double {
        if (minutos <= 0) {
            throw IllegalArgumentException(
                "tiempo de atencion invalido"
            )
        }
        var monto =
            paciente.calcularCostoBase(minutos)

        val felinoGratis =
            paciente is Felino && minutos < 20
        if (monto <= 0.0 && !felinoGratis) {
            throw IllegalArgumentException(
                "resultado de tarifa invalido"
            )
        }
        if (felinoGratis) {
            return 0.0
        }
        monto *= 1.19


        if (paciente.tipoDueno == TipoDueno.MUNICIPAL) {
            monto *= 0.50
        }
        return monto
    }
    fun registrarSalida(
        codigo: String,
        minutos: Int
    ) {
        for (box in boxes) {
            val estadoActual = box.estado
            if (estadoActual is EstadoBox.EnAtencion) {
                if (estadoActual.paciente.codigo == codigo) {
                    val paciente = estadoActual.paciente
                    box.estado =
                        EstadoBox.EnProceso("Calculando tarifa")
                    println(
                        "Procesando salida de ${paciente.nombre}"
                    )
                    try {
                        val monto =
                            calcularMontoFinal(
                                paciente,
                                minutos
                            )
                        val numeroTicket =
                            historial.size + 1
                        val ticket =
                            Ticket(
                                numero = numeroTicket,
                                paciente = paciente,
                                minutos = minutos,
                                montoPagado = monto
                            )
                        historial.add(ticket)
                        totalRecaudado += monto
                        box.estado = EstadoBox.Libre

                        println("Salida registrada correctamente")
                        println("Ticket numero: ${ticket.numero}")
                        println("Paciente: ${paciente.nombre}")
                        println("Tiempo: $minutos minutos")
                        println("Monto pagado: \$${ticket.montoPagado}")

                    } catch (error: IllegalArgumentException) {
                        println("AVISO: ${error.message}")
                        box.estado =
                            EstadoBox.EnAtencion(paciente)
                    }
                    return
                }
            }
        }
        println("AVISO: paciente no encontrado")
    }

    fun cantidadBoxesDisponibles(): Int {
        return boxes.count {
            it.estado is EstadoBox.Libre
        }
    }

    fun pacientesConvenio(): List<Paciente> {
        return historial
            .filter {
                it.paciente.tipoDueno ==
                        TipoDueno.CONVENIO
            }
            .map {
                it.paciente
            }
    }

    fun ingresoPromedio(): Double {
        if (historial.isEmpty()) {
            return 0.0
        }
        return historial
            .map {
                it.montoPagado
            }
            .average()
    }

    fun codigosFinalizados(): List<String> {
        return historial.map {
            it.paciente.codigo
        }
    }

    fun pacienteMayorTiempo(): Paciente? {
        val ticket =
            historial.maxByOrNull {
                it.minutos
            }
        return ticket?.paciente
    }
    fun totalCaninos(): Double {
        return historial
            .filter {
                it.paciente is Canino
            }
            .sumOf {
                it.montoPagado
            }
    }

    fun totalFelinos(): Double {
        return historial
            .filter {
                it.paciente is Felino
            }
            .sumOf {
                it.montoPagado
            }
    }
    fun totalExoticos(): Double {
        return historial
            .filter {
                it.paciente is Exotico
            }
            .sumOf {
                it.montoPagado
            }
    }

    fun tipoMayorIngreso(): String {
        val caninos = totalCaninos()
        val felinos = totalFelinos()
        val exoticos = totalExoticos()

        if (caninos >= felinos && caninos >= exoticos) {
            return "Canino"
        }
        if (felinos >= caninos && felinos >= exoticos) {
            return "Felino"
        }

        return "Exotico"
    }
    fun tipoPaciente(paciente: Paciente): String {
        return when (paciente) {
            is Canino -> "Canino"
            is Felino -> "Felino"
            is Exotico -> "Exotico"
            else -> "Paciente"
        }
    }
    fun mostrarConsultas() {
        println()
        println("--- CONSULTAS DEL SISTEMA ---")
        println(
            "Boxes disponibles: ${cantidadBoxesDisponibles()}"
        )
        println()
        println("Pacientes de convenio:")
        val convenios = pacientesConvenio()
        if (convenios.isEmpty()) {
            println("No hay pacientes de convenio")
        } else {
            for (paciente in convenios) {
                println("- ${paciente.nombre}")
            }
        }

        println()
        println(
            "Ingreso promedio: \$${ingresoPromedio()}"
        )

        println()
        println("Codigos finalizados:")
        val codigos = codigosFinalizados()
        for (codigo in codigos) {
            println("- $codigo")
        }
        println()
        val pacienteMayor =
            pacienteMayorTiempo()

        if (pacienteMayor != null) {
            println(
                "Paciente con mayor tiempo: " +
                        pacienteMayor.nombre
            )
        }
    }

    fun reporteCierre() {
        println()
        println("============================")
        println("REPORTE DE CIERRE PETCARE")
        println("============================")

        for (ticket in historial) {
            println()
            println(
                "Ticket numero: ${ticket.numero}"
            )
            println(
                "Tipo: ${tipoPaciente(ticket.paciente)}"
            )
            println(
                "Codigo: ${ticket.paciente.codigo}"
            )
            println(
                "Paciente: ${ticket.paciente.nombre}"
            )
            println(
                "Tiempo: ${ticket.minutos} minutos"
            )
            println(
                "Monto: \$${ticket.montoPagado}"
            )

            if (ticket.paciente is Exotico) {
                println(
                    "Silvestre: ${ticket.paciente.silvestre}"
                )
            }
        }
        println()
        println("----------------------------")
        println(
            "Total recaudado: \$$totalRecaudado"
        )
        println(
            "Pacientes atendidos: ${historial.size}"
        )
        println(
            "Ingreso promedio: \$${ingresoPromedio()}"
        )
        println(
            "Tipo con mayor ingreso: ${tipoMayorIngreso()}"
        )
        println(
            "Boxes disponibles: ${cantidadBoxesDisponibles()}"
        )
    }
}