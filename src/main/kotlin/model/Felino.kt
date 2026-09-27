package org.example.model

import java.time.LocalDateTime

class Felino(
    codigo: String,
    nombre: String,
    especie: String,
    fechaIngreso: LocalDateTime,
    tipoDueno: TipoDueno
) : Paciente(
    codigo,
    nombre,
    especie,
    fechaIngreso,
    tipoDueno
) {
    override fun calcularCostoBase(minutos: Int): Double {
        if (minutos < 20) {
            return 0.0
        }
        val horas = minutos / 60.0
        return horas * 9000.0
    }
}