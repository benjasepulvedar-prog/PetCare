package org.example.model

import java.time.LocalDateTime

open class Paciente(
    val codigo: String,
    val nombre: String,
    val especie: String,
    val fechaIngreso: LocalDateTime,
    val tipoDueno: TipoDueno
) {
    open fun calcularCostoBase(minutos: Int): Double {
        return 0.0
    }
}