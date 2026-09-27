package org.example.model

import java.time.LocalDateTime

class Exotico(
    codigo: String,
    nombre: String,
    especie: String,
    fechaIngreso: LocalDateTime,
    tipoDueno: TipoDueno,
    val silvestre: Boolean
) : Paciente(
    codigo,
    nombre,
    especie,
    fechaIngreso,
    tipoDueno
) {

    override fun calcularCostoBase(minutos: Int): Double {
        val horas = minutos / 60.0
        var costo = horas * 20000.0
        if (silvestre) {
            costo *= 1.30
        }
        return costo
    }
}