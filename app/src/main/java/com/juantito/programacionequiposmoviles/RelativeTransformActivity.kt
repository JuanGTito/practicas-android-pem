package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.view.View
import android.widget.Button

class RelativeTransformActivity : LayoutDemoActivity(R.layout.activity_relative_transform) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val muestra = findViewById<View>(R.id.muestra)
        val desplazamiento = 24 * resources.displayMetrics.density
        var paso = 0

        fun aplicarTransformacion() {
            // Las transformaciones cambian el dibujo y conservan el espacio asignado por RelativeLayout.
            muestra.translationX = if (paso == 1) desplazamiento else 0f
            muestra.scaleX = if (paso == 2) 1.25f else 1f
            muestra.scaleY = muestra.scaleX
            muestra.rotation = if (paso == 3) 18f else 0f

            val mensaje = when (paso) {
                1 -> R.string.transformacion_trasladar
                2 -> R.string.transformacion_escalar
                3 -> R.string.transformacion_rotar
                else -> R.string.transformacion_reiniciar
            }
            avisar(getString(mensaje))
        }

        findViewById<Button>(R.id.btn_transformar).setOnClickListener {
            paso = (paso % 3) + 1
            aplicarTransformacion()
        }
        findViewById<Button>(R.id.btn_reiniciar).setOnClickListener {
            paso = 0
            aplicarTransformacion()
        }
    }
}
