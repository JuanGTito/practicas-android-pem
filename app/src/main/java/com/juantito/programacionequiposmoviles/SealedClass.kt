package com.juantito.programacionequiposmoviles

import android.util.Log

// 1.10 Sealed class -----------------------------------------------------------------------------

sealed class Estado {
    data object Cargando : Estado()
    data class Exito(val datos: String) : Estado()
    data class Error(val mensaje: String) : Estado()
}

fun manejarEstado(estado: Estado) {
    when (estado) {
        Estado.Cargando -> Log.d(TAG_PRACTICA_03, "Cargando...")
        is Estado.Exito -> Log.d(TAG_PRACTICA_03, "Datos: ${estado.datos}")
        is Estado.Error -> Log.d(TAG_PRACTICA_03, "Error: ${estado.mensaje}")
    }
}

sealed class ResultadoSellado<out T> {
    data class Exito<T>(val valor: T) : ResultadoSellado<T>()
    data class Fallo(val error: Exception) : ResultadoSellado<Nothing>()
    data object Pendiente : ResultadoSellado<Nothing>()
}

fun <T> mostrarResultado(resultado: ResultadoSellado<T>): String = when (resultado) {
    is ResultadoSellado.Exito -> "Éxito: ${resultado.valor}"
    is ResultadoSellado.Fallo -> "Fallo: ${resultado.error.message}"
    ResultadoSellado.Pendiente -> "Pendiente..."
}

enum class Direccion { NORTE, SUR, ESTE, OESTE }

sealed class FormaSellada {
    data class Circulo(val radio: Double) : FormaSellada()
    data class Rectangulo(val ancho: Double, val alto: Double) : FormaSellada()
    data object Punto : FormaSellada()
}

fun calcularArea(forma: FormaSellada): Double = when (forma) {
    is FormaSellada.Circulo -> Math.PI * forma.radio * forma.radio
    is FormaSellada.Rectangulo -> forma.ancho * forma.alto
    FormaSellada.Punto -> 0.0
}

sealed class UiState<out T> {
    data object Idle : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String, val code: Int = 0) : UiState<Nothing>()
}

fun renderizarEstado(estado: UiState<List<String>>) {
    when (estado) {
        UiState.Idle -> Log.d(TAG_PRACTICA_03, "En espera")
        UiState.Loading -> Log.d(TAG_PRACTICA_03, "Mostrando spinner...")
        is UiState.Success -> Log.d(TAG_PRACTICA_03, "Items: ${estado.data.size}")
        is UiState.Error -> Log.d(TAG_PRACTICA_03, "Error ${estado.code}: ${estado.message}")
    }
}

fun ejecutarSealedClass() {
    manejarEstado(Estado.Cargando)
    manejarEstado(Estado.Exito("Lista de usuarios"))
    manejarEstado(Estado.Error("Sin conexión"))

    Log.d(TAG_PRACTICA_03, mostrarResultado(ResultadoSellado.Exito(42)))
    Log.d(TAG_PRACTICA_03, mostrarResultado(ResultadoSellado.Fallo(Exception("Timeout"))))
    Log.d(TAG_PRACTICA_03, mostrarResultado(ResultadoSellado.Pendiente))

    Log.d(TAG_PRACTICA_03, "Área círculo: ${calcularArea(FormaSellada.Circulo(5.0))}")
    Log.d(TAG_PRACTICA_03, "Área rectángulo: ${calcularArea(FormaSellada.Rectangulo(4.0, 3.0))}")
    Log.d(TAG_PRACTICA_03, "Dirección enum: ${Direccion.NORTE}")

    renderizarEstado(UiState.Loading)
    renderizarEstado(UiState.Success(listOf("A", "B", "C")))
    renderizarEstado(UiState.Error("Sin conexión", 503))
}
