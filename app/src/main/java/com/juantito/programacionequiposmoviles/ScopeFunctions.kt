package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.12 Scope functions: let, run, apply, also y with -------------------------------------------

data class UsuarioScope(val nombre: String, val email: String)
data class ConfiguracionScope(var host: String = "", var puerto: Int = 0)
data class PersonaScope(var nombre: String = "", var edad: Int = 0, var email: String = "")
data class RectanguloScope(val ancho: Double, val alto: Double)

fun ejecutarScopeFunctions() {
    // let usa it y devuelve el resultado de la lambda.
    val usuario: UsuarioScope? = UsuarioScope("Jean", "jean@mail.com")
    usuario?.let {
        Log.d(TAG_PRACTICA_03, "Nombre: ${it.nombre}")
        Log.d(TAG_PRACTICA_03, "Email: ${it.email}")
    }
    val longitud = "Kotlin".let { it.length * 2 }
    val resultado = listOf(1, 2, 3).map { it * 2 }.let { "Resultado: $it" }
    Log.d(TAG_PRACTICA_03, "Longitud: $longitud")
    Log.d(TAG_PRACTICA_03, resultado)

    // run usa this y devuelve el resultado de la lambda.
    val configuracion = ConfiguracionScope().run {
        host = "localhost"
        puerto = 8080
        Log.d(TAG_PRACTICA_03, "Configuración lista: $host:$puerto")
        this
    }
    val saludo = "Jean".run { "Hola, ${uppercase()}!" }
    Log.d(TAG_PRACTICA_03, "$saludo Configuración: $configuracion")

    // apply configura un objeto y devuelve el mismo objeto.
    val persona = PersonaScope().apply {
        nombre = "Jean Farfan"
        edad = 25
        email = "jean@mail.com"
    }
    val lenguajes = mutableListOf<String>().apply {
        add("Kotlin")
        add("Java")
        add("Python")
    }
    Log.d(TAG_PRACTICA_03, "$persona")
    Log.d(TAG_PRACTICA_03, "$lenguajes")

    // also conserva el objeto y sirve para registrar efectos secundarios en una cadena.
    val numeros = mutableListOf(3, 1, 4, 1, 5, 9, 2, 6)
        .also { Log.d(TAG_PRACTICA_03, "Original: $it") }
        .sorted()
        .also { Log.d(TAG_PRACTICA_03, "Ordenado: $it") }
        .filter { it > 3 }
        .also { Log.d(TAG_PRACTICA_03, "Filtrado: $it") }
    Log.d(TAG_PRACTICA_03, "Resultado de also: $numeros")

    // with recibe el objeto como parámetro y devuelve el resultado de la lambda.
    val rectangulo = RectanguloScope(4.0, 3.0)
    val informacion = with(rectangulo) {
        val area = ancho * alto
        val perimetro = 2 * (ancho + alto)
        "Área: $area, perímetro: $perimetro"
    }
    Log.d(TAG_PRACTICA_03, informacion)
}
