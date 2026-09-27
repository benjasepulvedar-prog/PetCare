package org.example

import org.example.model.Canino
import org.example.model.Exotico
import org.example.model.Felino
import org.example.model.TipoDueno
import org.example.service.PetCare
import java.time.LocalDateTime

fun main() {

    val sistema = PetCare()

    val max = Canino(
        codigo = "CA12CD",
        nombre = "Max",
        especie = "Golden Retriever",
        fechaIngreso = LocalDateTime.now(),
        tipoDueno = TipoDueno.CONVENIO
    )
    val luna = Canino(
        codigo = "CA99ZA",
        nombre = "Luna",
        especie = "Labrador",
        fechaIngreso = LocalDateTime.now(),
        tipoDueno = TipoDueno.PARTICULAR
    )
    val misi = Felino(
        codigo = "FE22TO",
        nombre = "Misi",
        especie = "Siames",
        fechaIngreso = LocalDateTime.now(),
        tipoDueno = TipoDueno.PARTICULAR
    )
    val loro = Exotico(
        codigo = "EX44RG",
        nombre = "Loro",
        especie = "Amazonico",
        fechaIngreso = LocalDateTime.now(),
        tipoDueno = TipoDueno.MUNICIPAL,
        silvestre = true
    )

    val iguana = Exotico(
        codigo = "EX77RG",
        nombre = "Iguana",
        especie = "Verde",
        fechaIngreso = LocalDateTime.now(),
        tipoDueno = TipoDueno.PARTICULAR,
        silvestre = false
    )

    println("--- ENTRADAS ---")
    sistema.registrarEntrada(max)
    sistema.registrarEntrada(luna)
    sistema.registrarEntrada(misi)
    sistema.registrarEntrada(loro)
    sistema.registrarEntrada(iguana)
    println()
    println("--- BOXES OCUPADOS ---")
    sistema.mostrarBoxes()

    println()
    println("--- PRUEBA CODIGO INVALIDO ---")
    val pacienteError = Canino(
        codigo = "123ABC",
        nombre = "Rocky",
        especie = "Pastor Aleman",
        fechaIngreso = LocalDateTime.now(),
        tipoDueno = TipoDueno.PARTICULAR
    )
    sistema.registrarEntrada(pacienteError)

    println()
    println("--- PRUEBA ERROR DE TARIFA ---")

    sistema.registrarSalida(
        codigo = "CA12CD",
        minutos = 0
    )

    println()
    println("--- SALIDAS ---")
    sistema.registrarSalida(
        codigo = "CA12CD",
        minutos = 75
    )
    sistema.registrarSalida(
        codigo = "CA99ZA",
        minutos = 180
    )
    sistema.registrarSalida(
        codigo = "FE22TO",
        minutos = 18
    )

    sistema.registrarSalida(
        codigo = "EX44RG",
        minutos = 120
    )
    sistema.registrarSalida(
        codigo = "EX77RG",
        minutos = 45
    )
    println()
    println("--- PRUEBA PACIENTE NO ENCONTRADO ---")
    sistema.registrarSalida(
        codigo = "ZZ99ZZ",
        minutos = 30
    )
    sistema.mostrarConsultas()
    sistema.reporteCierre()
}