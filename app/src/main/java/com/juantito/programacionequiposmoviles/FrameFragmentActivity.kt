package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment

class FrameFragmentActivity : LayoutDemoActivity(R.layout.activity_frame_fragment) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // FragmentManager restaura el fragmento que ya estaba abierto al recrear la pantalla.
        if (savedInstanceState == null) mostrarFragmento(FrameInicioFragment())
        findViewById<Button>(R.id.btn_inicio).setOnClickListener {
            mostrarFragmento(FrameInicioFragment())
        }
        findViewById<Button>(R.id.btn_reporte).setOnClickListener {
            mostrarFragmento(ReporteFragment())
        }
    }

    private fun mostrarFragmento(fragmento: Fragment) {
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.frame_container, fragmento)
            .commit()
        Log.d(TAG_LAYOUTS, "Mostrando ${fragmento.javaClass.simpleName}")
    }
}
