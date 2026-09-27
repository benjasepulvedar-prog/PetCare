package org.example.model

data class Ticket(
    val numero: Int,
    val paciente: Paciente,
    val minutos: Int,
    val montoPagado: Double
)