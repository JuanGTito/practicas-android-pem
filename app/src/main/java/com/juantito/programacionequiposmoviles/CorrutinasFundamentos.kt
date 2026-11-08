package com.juantito.programacionequiposmoviles

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// 1.14 Corrutinas: fundamentos -----------------------------------------------------------------

suspend fun obtenerDatos(id: Int): String {
    delay(100)
    return "Datos del usuario $id"
}

suspend fun procesarUsuario(id: Int) {
    Log.d(TAG_PRACTICA_03, "Procesado: ${obtenerDatos(id)}")
}

suspend fun calcularCuadrado(numero: Int): Int {
    delay(100)
    return numero * numero
}

suspend fun obtenerNombreAsincrono(id: Int): String {
    delay(150)
    return "Usuario$id"
}

suspend fun obtenerEmailAsincrono(id: Int): String {
    delay(120)
    return "user$id@mail.com"
}

suspend fun obtenerSaldoAsincrono(id: Int): Double {
    delay(90)
    return id * 100.0
}

suspend fun ejecutarCorrutinasFundamentos() = coroutineScope {
    // launch devuelve un Job y se usa cuando no se requiere un valor de retorno.
    val tarea = launch {
        Log.d(TAG_PRACTICA_03, "Corrutina iniciada")
        delay(100)
        Log.d(TAG_PRACTICA_03, "Corrutina reanudada")
    }

    procesarUsuario(1)
    procesarUsuario(2)

    // async devuelve Deferred; await recupera el resultado.
    val cuadradoCuatro = async { calcularCuadrado(4) }
    val cuadradoCinco = async { calcularCuadrado(5) }
    Log.d(TAG_PRACTICA_03, "4² = ${cuadradoCuatro.await()}, 5² = ${cuadradoCinco.await()}")
    tarea.join()

    // Las tres tareas comienzan antes de esperar sus resultados y trabajan en paralelo.
    val inicio = System.currentTimeMillis()
    val nombre = async { obtenerNombreAsincrono(1) }
    val email = async { obtenerEmailAsincrono(1) }
    val saldo = async { obtenerSaldoAsincrono(1) }
    Log.d(TAG_PRACTICA_03, "Nombre: ${nombre.await()}")
    Log.d(TAG_PRACTICA_03, "Email: ${email.await()}")
    Log.d(TAG_PRACTICA_03, "Saldo: ${saldo.await()}")
    Log.d(TAG_PRACTICA_03, "Tiempo paralelo: ${System.currentTimeMillis() - inicio} ms")

    // Dispatchers.IO sirve para entrada/salida y Default para cálculos de CPU.
    val datoDesdeIo = withContext(Dispatchers.IO) {
        Log.d(TAG_PRACTICA_03, "Trabajo en hilo IO")
        "resultado desde IO"
    }
    val calculoDesdeDefault = withContext(Dispatchers.Default) {
        Log.d(TAG_PRACTICA_03, "Trabajo en hilo Default")
        (1..100).sum()
    }
    Log.d(TAG_PRACTICA_03, "$datoDesdeIo; suma en Default: $calculoDesdeDefault")
}
