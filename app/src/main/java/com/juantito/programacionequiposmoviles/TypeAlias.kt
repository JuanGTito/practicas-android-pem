package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.13 typealias --------------------------------------------------------------------------------

typealias Nombre = String
typealias Edad = Int
typealias Usuarios = List<String>

typealias IdNombre = Pair<Int, String>
typealias ListaRegistros = List<IdNombre>
typealias IndiceUsuarios = Map<String, ListaRegistros>

typealias Validador<T> = (T) -> Boolean
typealias Transformador = (String) -> String
typealias Callback = (Boolean, String?) -> Unit
typealias EmailAlias = String

data class Email(val valor: String)

fun validarEmailAlias(email: String, validador: Validador<String>): Boolean = validador(email)

fun aplicarTransformacion(texto: String, funcion: Transformador): String = funcion(texto)

fun procesarPago(monto: Double, alObtenerResultado: Callback) {
    val exito = monto > 0
    alObtenerResultado(exito, if (exito) null else "Monto inválido")
}

fun ejecutarTypeAlias() {
    val nombre: Nombre = "Jean"
    val edad: Edad = 25
    val usuarios: Usuarios = listOf("Ana", "Luis")
    Log.d(TAG_PRACTICA_03, "$nombre tiene $edad años. Usuarios: $usuarios")

    val datos: IndiceUsuarios = mapOf(
        "activos" to listOf(1 to "Ana", 2 to "Luis"),
        "inactivos" to listOf(3 to "Marta")
    )
    for ((grupo, registros) in datos) {
        Log.d(TAG_PRACTICA_03, "Grupo: $grupo")
        for ((id, nombreRegistro) in registros) {
            Log.d(TAG_PRACTICA_03, "ID: $id, nombre: $nombreRegistro")
        }
    }

    val emailValido: Validador<String> = { it.contains("@") }
    val mayusculas: Transformador = { it.uppercase() }
    Log.d(TAG_PRACTICA_03, "Email válido: ${validarEmailAlias("jean@mail.com", emailValido)}")
    Log.d(TAG_PRACTICA_03, aplicarTransformacion("kotlin", mayusculas))
    procesarPago(150.0) { exito, error ->
        if (exito) Log.d(TAG_PRACTICA_03, "Pago OK")
        else Log.d(TAG_PRACTICA_03, "Error: $error")
    }

    // El alias sigue siendo String; la data class sí crea un tipo diferente.
    val alias: EmailAlias = "jean@mail.com"
    val texto: String = alias
    val email = Email(texto)
    Log.d(TAG_PRACTICA_03, email.valor)
}
