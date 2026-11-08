package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.1 Funciones de orden superior ---------------------------------------------------------------

fun ejecutarOperacion(a: Int, b: Int, accion: (Int, Int) -> Int): Int = accion(a, b)

fun operarLista(lista: List<Int>, condicion: (Int) -> Boolean): List<Int> {
    val resultado = mutableListOf<Int>()
    for (elemento in lista) {
        if (condicion(elemento)) resultado.add(elemento)
    }
    return resultado
}

fun crearMultiplicador(factor: Int): (Int) -> Int = { numero -> numero * factor }

fun combinarFunciones(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int = { x -> g(f(x)) }

fun ejecutarFuncionesOrdenSuperior() {
    // Una función puede almacenarse en una variable y enviarse como parámetro.
    val operacion: (Int, Int) -> Int = { a, b -> a + b }
    val resultado = ejecutarOperacion(10, 5, operacion)
    Log.d(TAG_PRACTICA_03, "Resultado: $resultado")

    val numeros = (1..10).toList()
    val pares = operarLista(numeros) { it % 2 == 0 }
    val mayoresDeCinco = operarLista(numeros) { it > 5 }
    Log.d(TAG_PRACTICA_03, "Pares: $pares")
    Log.d(TAG_PRACTICA_03, "Mayores a 5: $mayoresDeCinco")

    // Una función también puede devolver otra función.
    val doble = crearMultiplicador(2)
    val triple = crearMultiplicador(3)
    Log.d(TAG_PRACTICA_03, "Doble de 5: ${doble(5)}")
    Log.d(TAG_PRACTICA_03, "Triple de 5: ${triple(5)}")

    // Aquí se recibe una función y se devuelve una nueva función combinada.
    val sumarUno = { x: Int -> x + 1 }
    val duplicar = { x: Int -> x * 2 }
    val sumarYDuplicar = combinarFunciones(sumarUno, duplicar)
    Log.d(TAG_PRACTICA_03, "(4 + 1) * 2 = ${sumarYDuplicar(4)}")
}
