package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.2 Funciones inline, noinline y crossinline --------------------------------------------------

fun ejecutarNormal(accion: () -> Unit) = accion()

inline fun ejecutarInline(accion: () -> Unit) = accion()

inline fun buscarPrimero(lista: List<Int>, condicion: (Int) -> Boolean): Int? {
    for (elemento in lista) {
        if (condicion(elemento)) return elemento
    }
    return null
}

inline fun procesarDos(accion1: () -> Unit, noinline accion2: () -> Unit) {
    accion1()
    val guardada = accion2
    guardada()
}

inline fun ejecutarEnOtroContexto(crossinline accion: () -> Unit) {
    val tarea = Runnable { accion() }
    tarea.run()
}

fun ejecutarFuncionesInline() {
    ejecutarNormal { Log.d(TAG_PRACTICA_03, "Lambda ejecutada sin inline") }
    ejecutarInline { Log.d(TAG_PRACTICA_03, "Lambda copiada en el lugar de llamada") }

    val numeros = listOf(3, 7, 2, 9, 4)
    val resultado = buscarPrimero(numeros) { it > 5 }
    Log.d(TAG_PRACTICA_03, "Primero mayor a 5: $resultado")

    procesarDos(
        accion1 = { Log.d(TAG_PRACTICA_03, "Acción 1 inlineada") },
        accion2 = { Log.d(TAG_PRACTICA_03, "Acción 2 guardada con noinline") }
    )

    ejecutarEnOtroContexto {
        Log.d(TAG_PRACTICA_03, "Acción segura en otro contexto con crossinline")
    }
}
