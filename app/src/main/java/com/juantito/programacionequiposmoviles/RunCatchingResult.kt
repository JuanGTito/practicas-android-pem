package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.9 runCatching y Result ----------------------------------------------------------------------

fun convertirConResult(texto: String): Result<Int> = runCatching { texto.toInt() }

fun parsearConTryCatch(texto: String): Int = try {
    texto.toInt()
} catch (error: NumberFormatException) {
    Log.d(TAG_PRACTICA_03, "Formato inválido: $texto")
    0
}

fun parsearConResult(texto: String): Int = runCatching { texto.toInt() }
    .onFailure { Log.d(TAG_PRACTICA_03, "Formato inválido: $texto") }
    .getOrDefault(0)

fun ejecutarRunCatchingResult() {
    val resultado: Result<Int> = runCatching { "42".toInt() }
    if (resultado.isSuccess) {
        Log.d(TAG_PRACTICA_03, "Éxito: ${resultado.getOrNull()}")
    } else {
        Log.d(TAG_PRACTICA_03, "Fallo: ${resultado.exceptionOrNull()?.message}")
    }

    convertirConResult("100")
        .onSuccess { Log.d(TAG_PRACTICA_03, "Convertido: $it") }
        .onFailure { Log.d(TAG_PRACTICA_03, "Error: ${it.message}") }
    convertirConResult("abc")
        .onSuccess { Log.d(TAG_PRACTICA_03, "Convertido: $it") }
        .onFailure { Log.d(TAG_PRACTICA_03, "Error: ${it.message}") }

    val r1 = runCatching { "42".toInt() }.getOrDefault(0)
    val r2 = runCatching { "abc".toInt() }.getOrDefault(0)
    val r3 = runCatching { "abc".toInt() }.getOrElse { error ->
        Log.d(TAG_PRACTICA_03, "Usando default por: ${error.message}")
        -1
    }
    val r4 = runCatching { "10".toInt() }.map { it * 2 }.getOrDefault(0)
    Log.d(TAG_PRACTICA_03, "r1=$r1, r2=$r2, r3=$r3, r4=$r4")

    Log.d(TAG_PRACTICA_03, "try-catch: ${parsearConTryCatch("abc")}")
    Log.d(TAG_PRACTICA_03, "Result: ${parsearConResult("abc")}")
}
