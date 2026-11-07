package com.juantito.programacionequiposmoviles

import android.os.Bundle

class ReporteActivity : LayoutDemoActivity(R.layout.activity_reporte) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Un Fragment necesita un contenedor administrado por una Activity.
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.reporte_container, ReporteFragment())
                .commit()
        }
    }
}
