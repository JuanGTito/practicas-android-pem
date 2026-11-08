package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.3 Recursión con tailrec --------------------------------------------------------------------

fun factorialNormal(n: Int): Long {
    if (n <= 1) return 1
    return n * factorialNormal(n - 1)
}

tailrec fun factorialTailrec(n: Int, acumulador: Long = 1): Long {
    if (n <= 1) return acumulador
    return factorialTailrec(n - 1, n * acumulador)
}

tailrec fun buscarRecursivo(lista: List<Int>, objetivo: Int, indice: Int = 0): Int {
    if (indice >= lista.size) return -1
    if (lista[indice] == objetivo) return indice
    return buscarRecursivo(lista, objetivo, indice + 1)
}

tailrec fun sumarListaRecursiva(lista: List<Int>, indice: Int = 0, acumulador: Int = 0): Int {
    if (indice >= lista.size) return acumulador
    return sumarListaRecursiva(lista, indice + 1, acumulador + lista[indice])
}

fun ejecutarRecursionTailrec() {
    Log.d(TAG_PRACTICA_03, "Factorial normal: ${factorialNormal(10)}")
    Log.d(TAG_PRACTICA_03, "Factorial tailrec: ${factorialTailrec(10)}")

    val numeros = listOf(3, 7, 2, 9, 4)
    Log.d(TAG_PRACTICA_03, "Posición de 9: ${buscarRecursivo(numeros, 9)}")
    Log.d(TAG_PRACTICA_03, "Suma recursiva: ${sumarListaRecursiva(numeros)}")
}
