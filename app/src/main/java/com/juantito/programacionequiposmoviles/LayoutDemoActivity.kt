package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity

internal const val TAG_LAYOUTS = "PRACTICA-CAPITULO-11"

abstract class LayoutDemoActivity(@param:LayoutRes private val layoutId: Int) : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layoutId)

        // Aplicamos los bordes al contenedor de la Activity para conservar el padding de cada XML.
        val contenido = findViewById<View>(android.R.id.content)
        ViewCompat.setOnApplyWindowInsetsListener(contenido) { view, insets ->
            val bordes = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout() or
                    WindowInsetsCompat.Type.ime()
            )
            view.setPadding(bordes.left, bordes.top, bordes.right, bordes.bottom)
            insets
        }
    }

    protected fun avisar(mensaje: String) {
        Log.d(TAG_LAYOUTS, mensaje)
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    protected fun avisarBotones(@IdRes vararg ids: Int) {
        ids.forEach { id ->
            val boton = findViewById<Button>(id)
            boton.setOnClickListener { avisar(getString(R.string.seleccion_layout, boton.text)) }
        }
    }
}
