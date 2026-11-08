package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.5 Operadores funcionales sobre colecciones --------------------------------------------------

data class ProductoColeccion(val nombre: String, val precio: Double, val stock: Int)

fun ejecutarOperadoresColecciones() {
    val numeros = (1..10).toList()

    // filter selecciona elementos y map transforma cada elemento seleccionado.
    val cuadradosPares = numeros.filter { it % 2 == 0 }.map { it * it }
    Log.d(TAG_PRACTICA_03, "Cuadrados pares: $cuadradosPares")

    val nombres = listOf("Ana", "Luis", "Marta")
    Log.d(TAG_PRACTICA_03, "Longitudes: ${nombres.map { it.length }}")
    Log.d(TAG_PRACTICA_03, "Mayúsculas: ${nombres.map { it.uppercase() }}")
    Log.d(TAG_PRACTICA_03, "Letras con flatMap: ${nombres.flatMap { it.toList() }}")

    val pares = numeros.filter { it % 2 == 0 }
    val impares = numeros.filterNot { it % 2 == 0 }
    Log.d(TAG_PRACTICA_03, "Pares: $pares")
    Log.d(TAG_PRACTICA_03, "Impares: $impares")

    val primerosCinco = numeros.take(5)
    val suma = primerosCinco.reduce { acumulador, elemento -> acumulador + elemento }
    val producto = primerosCinco.reduce { acumulador, elemento -> acumulador * elemento }
    val sumaDesdeCien = primerosCinco.fold(100) { acumulador, elemento -> acumulador + elemento }
    val concatenado = primerosCinco.fold("") { acumulador, elemento -> "$acumulador$elemento" }
    Log.d(TAG_PRACTICA_03, "Suma: $suma, producto: $producto")
    Log.d(TAG_PRACTICA_03, "Desde 100: $sumaDesdeCien, concatenado: $concatenado")

    val palabras = listOf("Ana", "Luis", "Marta", "Al", "Bob")
    Log.d(TAG_PRACTICA_03, "Agrupadas: ${palabras.groupBy { it.length }}")
    val (cortas, largas) = palabras.partition { it.length <= 3 }
    Log.d(TAG_PRACTICA_03, "Cortas: $cortas, largas: $largas")

    Log.d(TAG_PRACTICA_03, "Primero mayor a 4: ${numeros.find { it > 4 }}")
    Log.d(TAG_PRACTICA_03, "¿Alguno > 4?: ${numeros.any { it > 4 }}")
    Log.d(TAG_PRACTICA_03, "¿Todos positivos?: ${numeros.all { it > 0 }}")
    Log.d(TAG_PRACTICA_03, "¿Ninguno > 10?: ${numeros.none { it > 10 }}")
    Log.d(TAG_PRACTICA_03, "Cantidad de pares: ${numeros.count { it % 2 == 0 }}")

    val productos = listOf(
        ProductoColeccion("Laptop", 3500.0, 5),
        ProductoColeccion("Mouse", 120.0, 0),
        ProductoColeccion("Teclado", 250.0, 3),
        ProductoColeccion("Monitor", 1800.0, 0),
        ProductoColeccion("Webcam", 300.0, 8)
    )
    productos
        .filter { it.stock > 0 }
        .sortedBy { it.precio }
        .map { "${it.nombre}: S/.${it.precio}" }
        .forEach { Log.d(TAG_PRACTICA_03, it) }
}
