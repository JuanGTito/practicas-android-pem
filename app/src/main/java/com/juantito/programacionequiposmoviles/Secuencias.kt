package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.6 Secuencias (Sequence) ---------------------------------------------------------------------

fun ejecutarSecuencias() {
    // Una secuencia evalúa cada elemento de forma perezosa y necesita una operación terminal.
    val secuencia = listOf(1, 2, 3, 4, 5).asSequence()
    val secuenciaDirecta = sequenceOf(1, 2, 3, 4, 5)
    val resultado = secuencia
        .filter { it % 2 == 0 }
        .map { it * 10 }
        .toList()
    Log.d(TAG_PRACTICA_03, "Secuencia transformada: $resultado")
    Log.d(TAG_PRACTICA_03, "Secuencia directa: ${secuenciaDirecta.toList()}")

    // La colección crea resultados intermedios; Sequence procesa un elemento por toda la cadena.
    val numeros = (1..10).toList()
    val resultadoColeccion = numeros.filter { it % 2 == 0 }.map { it * it }.first()
    val resultadoSecuencia = numeros.asSequence().filter { it % 2 == 0 }.map { it * it }.first()
    Log.d(TAG_PRACTICA_03, "Colección: $resultadoColeccion")
    Log.d(TAG_PRACTICA_03, "Secuencia: $resultadoSecuencia")

    // generateSequence puede producir valores infinitos porque solo calcula los solicitados.
    val naturales = generateSequence(1) { it + 1 }.take(10).toList()
    val multiplosDeTres = generateSequence(3) { it + 3 }.take(5).toList()
    val potenciasDeDos = generateSequence(1) { it * 2 }.takeWhile { it < 1000 }.toList()
    Log.d(TAG_PRACTICA_03, "Naturales: $naturales")
    Log.d(TAG_PRACTICA_03, "Múltiplos de 3: $multiplosDeTres")
    Log.d(TAG_PRACTICA_03, "Potencias de 2: $potenciasDeDos")
}
