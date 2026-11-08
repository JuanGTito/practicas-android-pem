package com.juantito.programacionequiposmoviles

import android.util.Log
import kotlin.properties.Delegates

// 1.11 Delegación con by ------------------------------------------------------------------------

interface Saludador {
    fun saludar()
}

class SaludadorSimple : Saludador {
    override fun saludar() {
        Log.d(TAG_PRACTICA_03, "¡Hola!")
    }
}

class ClaseConSaludo(delegado: Saludador) : Saludador by delegado

interface Volador {
    fun volar()
}

interface Nadador {
    fun nadar()
}

class PajaroImpl : Volador {
    override fun volar() {
        Log.d(TAG_PRACTICA_03, "El pájaro vuela")
    }
}

class PezImpl : Nadador {
    override fun nadar() {
        Log.d(TAG_PRACTICA_03, "El pez nada")
    }
}

class PatoDelegado(
    volador: Volador = PajaroImpl(),
    nadador: Nadador = PezImpl()
) : Volador by volador, Nadador by nadador {
    fun presentarse() = Log.d(TAG_PRACTICA_03, "Soy un pato")
}

class PerfilUsuarioDelegado {
    // lazy calcula el valor una sola vez, cuando se accede por primera vez.
    val configuracion: String by lazy {
        Log.d(TAG_PRACTICA_03, "Cargando configuración...")
        "config_por_defecto"
    }

    // observable permite reaccionar después de cada cambio.
    var nombre: String by Delegates.observable("Sin nombre") { _, anterior, nuevo ->
        Log.d(TAG_PRACTICA_03, "Nombre cambió: '$anterior' -> '$nuevo'")
    }

    // vetoable acepta el nuevo valor únicamente cuando la lambda devuelve true.
    var edad: Int by Delegates.vetoable(0) { _, _, nuevo -> nuevo >= 0 }
}

class FormularioDelegado {
    var email: String by Delegates.observable("") { _, _, nuevo ->
        Log.d(TAG_PRACTICA_03, "Email actualizado: $nuevo")
    }

    var precio: Double by Delegates.vetoable(0.0) { _, _, nuevo ->
        val valido = nuevo in 0.0..99999.0
        if (!valido) Log.d(TAG_PRACTICA_03, "Precio inválido: $nuevo")
        valido
    }
}

fun ejecutarDelegacion() {
    ClaseConSaludo(SaludadorSimple()).saludar()

    val pato = PatoDelegado()
    pato.volar()
    pato.nadar()
    pato.presentarse()

    val perfil = PerfilUsuarioDelegado()
    Log.d(TAG_PRACTICA_03, perfil.configuracion)
    perfil.nombre = "Jean"
    perfil.edad = 25
    perfil.edad = -5
    Log.d(TAG_PRACTICA_03, "Edad conservada: ${perfil.edad}")

    val formulario = FormularioDelegado()
    formulario.email = "jean@mail.com"
    formulario.precio = 150.0
    formulario.precio = -10.0
    Log.d(TAG_PRACTICA_03, "Precio conservado: ${formulario.precio}")
}
