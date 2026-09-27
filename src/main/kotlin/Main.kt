package org.example

import org.example.model.Canino
import org.example.model.Felino
import org.example.model.TipoDueno
import java.time.LocalDateTime
import org.example.model.Exotico

fun main() {

    val max = Canino(
        codigo = "CA12CD",
        nombre = "Max",
        especie = "Golden Retriever",
        fechaIngreso = LocalDateTime.now(),
        tipoDueno = TipoDueno.CONVENIO
    )

    val costoMax = max.calcularCostoBase(75)

    println("Costo base de Max: $$costoMax")


    val misi = Felino(
        codigo = "FE22TO",
        nombre = "Misi",
        especie = "Siamés",
        fechaIngreso = LocalDateTime.now(),
        tipoDueno = TipoDueno.PARTICULAR
    )
    val costoMisi = misi.calcularCostoBase(18)
    println("Costo base de Misi: $$costoMisi")
    val loro = Exotico(
        codigo = "EX44RG",
        nombre = "Loro",
        especie = "Amazónico",
        fechaIngreso = LocalDateTime.now(),
        tipoDueno = TipoDueno.MUNICIPAL,
        silvestre = true
    )
    val costoLoro = loro.calcularCostoBase(120)
    println("Costo base de Loro: $$costoLoro")
}