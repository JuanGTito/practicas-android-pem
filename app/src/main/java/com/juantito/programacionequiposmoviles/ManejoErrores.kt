package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.7 Manejo de errores con try, catch y finally -----------------------------------------------

fun convertirAInt(texto: String): Int = try {
    texto.toInt()
} catch (error: NumberFormatException) {
    Log.d(TAG_PRACTICA_03, "No se pudo convertir '$texto', usando 0")
    0
}

fun leerDato(fuente: String): String {
    Log.d(TAG_PRACTICA_03, "Abriendo fuente: $fuente")
    return try {
        if (fuente.isEmpty()) throw IllegalArgumentException("Fuente vacía")
        "Dato leído de $fuente"
    } catch (error: IllegalArgumentException) {
        Log.d(TAG_PRACTICA_03, "Error: ${error.message}")
        "Dato por defecto"
    } finally {
        Log.d(TAG_PRACTICA_03, "Cerrando fuente: $fuente")
    }
}

fun procesarEntrada(entrada: String, indice: Int) {
    try {
        val numero = entrada.toInt()
        val lista = listOf(10, 20, 30)
        val valor = lista[indice]
        Log.d(TAG_PRACTICA_03, "Número: $numero, valor en lista: $valor")
    } catch (error: NumberFormatException) {
        Log.d(TAG_PRACTICA_03, "Error de formato: '$entrada' no es un número")
    } catch (error: IndexOutOfBoundsException) {
        Log.d(TAG_PRACTICA_03, "Error de índice: posición $indice no existe")
    } catch (error: Exception) {
        Log.d(TAG_PRACTICA_03, "Error inesperado: ${error.message}")
    } finally {
        Log.d(TAG_PRACTICA_03, "Procesamiento finalizado")
    }
}

fun ejecutarManejoErrores() {
    // La variable se declara fuera para poder consultarla después del bloque.
    var resultado: Int?
    var origen = "sin definir"
    try {
        resultado = "42".toInt()
        origen = "conversión exitosa"
        Log.d(TAG_PRACTICA_03, "Conversión OK: $resultado")
    } catch (error: NumberFormatException) {
        resultado = 0
        origen = "error en conversión"
        Log.d(TAG_PRACTICA_03, "Error: ${error.message}")
    } finally {
        Log.d(TAG_PRACTICA_03, "Proceso terminado. Origen: $origen")
    }
    Log.d(TAG_PRACTICA_03, "Resultado final: $resultado")

    Log.d(TAG_PRACTICA_03, "a=${convertirAInt("123")}, b=${convertirAInt("abc")}")
    Log.d(TAG_PRACTICA_03, leerDato("archivo.txt"))
    Log.d(TAG_PRACTICA_03, leerDato(""))

    procesarEntrada("abc", 0)
    procesarEntrada("42", 10)
    procesarEntrada("42", 1)
}
