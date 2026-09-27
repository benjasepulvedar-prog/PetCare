package org.example.model

import java.time.LocalDateTime

class Canino(
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
        val horas = minutos / 60.0
        var costo = horas * 12000.0
        if (this.tipoDueno == TipoDueno.CONVENIO) {
            costo *= 0.80
        }
        return costo
    }
}