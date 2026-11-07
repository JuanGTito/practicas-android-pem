package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LinearNestedActivity : LayoutDemoActivity(R.layout.activity_linear_nested) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val texto = findViewById<TextView>(R.id.txt_pagina)
        val atras = findViewById<Button>(R.id.btn_atras)
        val siguiente = findViewById<Button>(R.id.btn_siguiente)
        segundaPagina = savedInstanceState?.getBoolean(SEGUNDA_PAGINA) == true

        fun mostrarPagina() {
            texto.setText(if (segundaPagina) R.string.pagina_dos else R.string.pagina_uno)
            atras.isEnabled = segundaPagina
            siguiente.isEnabled = !segundaPagina
        }

        atras.setOnClickListener {
            segundaPagina = false
            mostrarPagina()
            avisar(atras.text.toString())
        }
        siguiente.setOnClickListener {
            segundaPagina = true
            mostrarPagina()
            avisar(siguiente.text.toString())
        }
        mostrarPagina()
    }

    private var segundaPagina = false

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(SEGUNDA_PAGINA, segundaPagina)
    }

    private companion object {
        const val SEGUNDA_PAGINA = "segunda_pagina"
    }
}
