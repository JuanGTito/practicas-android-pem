package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.math.BigDecimal
import java.math.MathContext

class GridCalculatorActivity : LayoutDemoActivity(R.layout.activity_grid_calculator) {
    private enum class Operacion { SUMA, RESTA, MULTIPLICACION, DIVISION }

    private lateinit var visor: TextView
    private var entrada = "0"
    private var primerNumero: BigDecimal? = null
    private var operacion: Operacion? = null
    private var entradaNueva = true
    private var errorDivision = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        visor = findViewById(R.id.display)
        savedInstanceState?.let { estado ->
            entrada = estado.getString("entrada", "0")
            primerNumero = estado.getString("primer_numero")?.toBigDecimal()
            operacion = estado.getString("operacion")?.let(Operacion::valueOf)
            entradaNueva = estado.getBoolean("entrada_nueva")
            errorDivision = estado.getBoolean("error_division")
        }

        // El XML ubica las teclas en la cuadrícula. Kotlin se encarga de cada pulsación.
        val digitos = listOf(R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9)
        digitos.forEachIndexed { digito, id ->
            findViewById<Button>(id).setOnClickListener { escribir(digito.toString()) }
        }
        findViewById<Button>(R.id.btn_decimal).setOnClickListener { escribir(".") }
        findViewById<Button>(R.id.btn_limpiar).setOnClickListener { limpiar() }
        findViewById<Button>(R.id.btn_igual).setOnClickListener { calcular() }

        val operadores = mapOf(
            R.id.btn_sumar to Operacion.SUMA,
            R.id.btn_restar to Operacion.RESTA,
            R.id.btn_multiplicar to Operacion.MULTIPLICACION,
            R.id.btn_dividir to Operacion.DIVISION
        )
        operadores.forEach { (id, siguiente) ->
            findViewById<Button>(id).setOnClickListener {
                if (operacion != null && !entradaNueva) calcular()
                if (!errorDivision) {
                    primerNumero = entrada.toBigDecimal()
                    operacion = siguiente
                    entradaNueva = true
                }
            }
        }
        mostrarEntrada()
    }

    private fun escribir(caracter: String) {
        if (entradaNueva) entrada = "0"
        entradaNueva = false
        errorDivision = false
        // Conservamos un solo separador decimal y evitamos que la entrada desborde el visor.
        if (entrada.length >= 16 || (caracter == "." && entrada.contains('.'))) return
        entrada = if (entrada == "0" && caracter != ".") caracter else entrada + caracter
        mostrarEntrada()
    }

    private fun calcular() {
        val operador = operacion ?: return
        val a = primerNumero ?: return
        if (entradaNueva) return
        val b = entrada.toBigDecimal()
        operacion = null
        primerNumero = null
        entradaNueva = true

        if (operador == Operacion.DIVISION && b.signum() == 0) {
            errorDivision = true
            mostrarEntrada()
            Log.d(TAG_LAYOUTS, getString(R.string.error_division))
            return
        }

        val precision = MathContext.DECIMAL64
        val resultado = when (operador) {
            Operacion.SUMA -> a.add(b, precision)
            Operacion.RESTA -> a.subtract(b, precision)
            Operacion.MULTIPLICACION -> a.multiply(b, precision)
            Operacion.DIVISION -> a.divide(b, precision)
        }
        val sinCeros = resultado.stripTrailingZeros()
        entrada = if (sinCeros.scale() in -8..12) sinCeros.toPlainString() else sinCeros.toString()
        mostrarEntrada()
        Log.d(TAG_LAYOUTS, getString(R.string.operacion_calculada, entrada))
    }

    private fun limpiar() {
        entrada = "0"
        primerNumero = null
        operacion = null
        entradaNueva = true
        errorDivision = false
        mostrarEntrada()
    }

    private fun mostrarEntrada() {
        visor.text = if (errorDivision) getString(R.string.error_division) else entrada
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("entrada", entrada)
        outState.putString("primer_numero", primerNumero?.toString())
        outState.putString("operacion", operacion?.name)
        outState.putBoolean("entrada_nueva", entradaNueva)
        outState.putBoolean("error_division", errorDivision)
    }
}
