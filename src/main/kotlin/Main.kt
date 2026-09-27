package org.example

import org.example.model.Canino
import org.example.model.TipoDueno
import java.time.LocalDateTime

fun main() {

    val max = Canino(
        codigo = "CA12CD",
        nombre = "Max",
        especie = "Golden Retriever",
        fechaIngreso = LocalDateTime.now(),
        tipoDueno = TipoDueno.CONVENIO
    )

    val costo = max.calcularCostoBase(75)

    println("Costo base de Max: $$costo")
}