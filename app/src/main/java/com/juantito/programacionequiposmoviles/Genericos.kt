package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.4 Genéricos ---------------------------------------------------------------------------------

fun <T> mostrarGenerico(valor: T) = Log.d(TAG_PRACTICA_03, "Valor: $valor")

class CajaGenerica<T>(private val contenido: T) {
    fun obtener(): T = contenido
}

class PilaGenerica<T> {
    private val elementos = mutableListOf<T>()

    fun apilar(elemento: T) {
        elementos.add(elemento)
        Log.d(TAG_PRACTICA_03, "Apilado: $elemento")
    }

    fun desapilar(): T? = if (elementos.isEmpty()) null else elementos.removeAt(elementos.lastIndex)
    fun estaVacia(): Boolean = elementos.isEmpty()
    fun tamano(): Int = elementos.size
}

fun <T> intercambiar(lista: MutableList<T>, i: Int, j: Int) {
    val temporal = lista[i]
    lista[i] = lista[j]
    lista[j] = temporal
}

fun <T> primeroONull(lista: List<T>): T? = lista.firstOrNull()

fun <T : Comparable<T>> maximo(a: T, b: T): T = if (a > b) a else b

fun <T : Number> sumarNumeros(a: T, b: T): Double = a.toDouble() + b.toDouble()

open class AnimalGenerico(val nombre: String) {
    override fun toString(): String = nombre
}

class PerroGenerico(nombre: String) : AnimalGenerico(nombre)

// out indica que esta clase solo produce valores de tipo T.
class Productor<out T>(private val valor: T) {
    fun obtener(): T = valor
}

// in indica que esta clase solo consume valores de tipo T.
class Consumidor<in T> {
    fun procesar(valor: T) = Log.d(TAG_PRACTICA_03, "Procesando: $valor")
}

fun ejecutarGenericos() {
    mostrarGenerico(42)
    mostrarGenerico("Hola")
    val caja = CajaGenerica("Kotlin")
    Log.d(TAG_PRACTICA_03, "Contenido: ${caja.obtener()}")

    val pilaEnteros = PilaGenerica<Int>()
    pilaEnteros.apilar(1)
    pilaEnteros.apilar(2)
    Log.d(TAG_PRACTICA_03, "Desapilado: ${pilaEnteros.desapilar()}")
    Log.d(TAG_PRACTICA_03, "Tamaño: ${pilaEnteros.tamano()}, vacía: ${pilaEnteros.estaVacia()}")

    val pilaTextos = PilaGenerica<String>()
    pilaTextos.apilar("Kotlin")
    pilaTextos.apilar("Java")
    Log.d(TAG_PRACTICA_03, "Desapilado: ${pilaTextos.desapilar()}")

    val numeros = mutableListOf(1, 2, 3, 4)
    intercambiar(numeros, 0, 3)
    Log.d(TAG_PRACTICA_03, "Lista intercambiada: $numeros")
    Log.d(TAG_PRACTICA_03, "Primer nombre: ${primeroONull(listOf("Ana", "Luis"))}")
    Log.d(TAG_PRACTICA_03, "Primero vacío: ${primeroONull(emptyList<Int>())}")

    Log.d(TAG_PRACTICA_03, "Máximo entero: ${maximo(10, 25)}")
    Log.d(TAG_PRACTICA_03, "Máximo texto: ${maximo("Ana", "Luis")}")
    Log.d(TAG_PRACTICA_03, "Suma numérica: ${sumarNumeros(3, 4.5)}")

    val productorPerro: Productor<PerroGenerico> = Productor(PerroGenerico("Rex"))
    val productorAnimal: Productor<AnimalGenerico> = productorPerro
    Log.d(TAG_PRACTICA_03, "Productor covariante: ${productorAnimal.obtener().nombre}")

    val consumidorAnimal: Consumidor<AnimalGenerico> = Consumidor()
    val consumidorPerro: Consumidor<PerroGenerico> = consumidorAnimal
    consumidorPerro.procesar(PerroGenerico("Max"))
}
