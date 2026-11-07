package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText

class LinearLoginActivity : LayoutDemoActivity(R.layout.activity_linear_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val correo = findViewById<EditText>(R.id.input_email)
        val contrasena = findViewById<EditText>(R.id.input_password)
        findViewById<Button>(R.id.btn_login).setOnClickListener {
            // El formulario solo comprueba los datos localmente. El registro usa un mensaje genérico.
            when {
                !Patterns.EMAIL_ADDRESS.matcher(correo.text.trim()).matches() -> {
                    correo.error = getString(R.string.correo_invalido)
                }
                contrasena.length() < 6 -> {
                    contrasena.error = getString(R.string.contrasena_invalida)
                }
                else -> {
                    contrasena.text.clear()
                    avisar(getString(R.string.acceso_validado))
                }
            }
        }
    }
}
