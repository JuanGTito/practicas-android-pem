package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.8 Lanzamiento de excepciones con throw ------------------------------------------------------

class SaldoInsuficienteException(
    val saldoActual: Double,
    val montoSolicitado: Double
) : Exception("Saldo insuficiente. Disponible: $saldoActual, solicitado: $montoSolicitado")

class UsuarioNoEncontradoException(val id: Int) :
    Exception("No se encontró el usuario con ID: $id")

class CuentaBancariaExcepcion(private var saldo: Double) {
    fun retirar(monto: Double) {
        if (monto > saldo) throw SaldoInsuficienteException(saldo, monto)
        saldo -= monto
        Log.d(TAG_PRACTICA_03, "Retiro exitoso. Saldo: $saldo")
    }
}

fun dividirSeguro(a: Int, b: Int): Double {
    if (b == 0) throw ArithmeticException("No se puede dividir entre cero")
    return a.toDouble() / b
}

fun establecerEdad(edad: Int) {
    require(edad >= 0) { "La edad no puede ser negativa: $edad" }
    require(edad <= 150) { "La edad no es válida: $edad" }
    Log.d(TAG_PRACTICA_03, "Edad establecida: $edad")
}

fun obtenerUsuario(id: Int): String {
    check(id > 0) { "El ID debe ser positivo" }
    return "Usuario-$id"
}

fun buscarUsuario(id: Int): String? = mapOf(1 to "Ana", 2 to "Luis")[id]

fun obtenerNombreUsuario(id: Int): String =
    buscarUsuario(id) ?: throw UsuarioNoEncontradoException(id)

fun procesarId(id: Int) {
    val nombre = buscarUsuario(id) ?: throw IllegalArgumentException("ID inválido: $id")
    Log.d(TAG_PRACTICA_03, "Usuario encontrado: $nombre")
}

fun ejecutarLanzamientoExcepciones() {
    Log.d(TAG_PRACTICA_03, "División: ${dividirSeguro(10, 2)}")
    establecerEdad(25)
    Log.d(TAG_PRACTICA_03, obtenerUsuario(1))

    val cuenta = CuentaBancariaExcepcion(100.0)
    try {
        cuenta.retirar(150.0)
    } catch (error: SaldoInsuficienteException) {
        Log.d(TAG_PRACTICA_03, "Error: ${error.message}")
        Log.d(TAG_PRACTICA_03, "Saldo actual: ${error.saldoActual}")
    }

    Log.d(TAG_PRACTICA_03, obtenerNombreUsuario(1))
    procesarId(2)
}
