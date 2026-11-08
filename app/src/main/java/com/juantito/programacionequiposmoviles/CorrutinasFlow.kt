package com.juantito.programacionequiposmoviles

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

// 1.15 Corrutinas: Flow -------------------------------------------------------------------------

fun contarHasta(limite: Int): Flow<Int> = flow {
    for (numero in 1..limite) {
        delay(50)
        emit(numero)
    }
}

fun flujoDeEventos(): Flow<String> = flow {
    emit("Iniciando conexión")
    delay(50)
    emit("Conexión establecida")
    delay(50)
    emit("Datos recibidos")
    emit("Conexión cerrada")
}

fun numerosFlow(): Flow<Int> = flow {
    for (numero in 1..10) {
        emit(numero)
        delay(20)
    }
}

fun temperaturasFlow(): Flow<Double> = flow {
    val temperaturas = listOf(22.5, 23.1, 21.8, 24.0, 22.9)
    for (temperatura in temperaturas) {
        emit(temperatura)
        delay(20)
    }
}

suspend fun ejecutarCorrutinasFlow() {
    // Flow es frío: solo comienza a emitir cuando se llama a collect.
    contarHasta(5).collect { valor -> Log.d(TAG_PRACTICA_03, "Recibido: $valor") }

    flujoDeEventos().collect { evento -> Log.d(TAG_PRACTICA_03, "Evento: $evento") }

    numerosFlow()
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(3)
        .collect { Log.d(TAG_PRACTICA_03, "Cuadrado par: $it") }

    temperaturasFlow().collect { Log.d(TAG_PRACTICA_03, "Temperatura: $it °C") }
    val lista = temperaturasFlow().toList()
    val primera = temperaturasFlow().first()
    Log.d(TAG_PRACTICA_03, "Total lecturas: ${lista.size}")
    Log.d(TAG_PRACTICA_03, "Primera temperatura: $primera °C")

    // Sequence es perezosa pero síncrona; Flow puede llamar a funciones suspendidas.
    val secuencia = sequence {
        yield(1)
        yield(2)
        yield(3)
    }
    val flujo = flow {
        emit(1)
        delay(20)
        emit(2)
        delay(20)
        emit(3)
    }
    Log.d(TAG_PRACTICA_03, "Sequence: ${secuencia.toList()}")
    Log.d(TAG_PRACTICA_03, "Flow: ${flujo.toList()}")
}
