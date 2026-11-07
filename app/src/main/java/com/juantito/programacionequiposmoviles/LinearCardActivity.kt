package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.widget.Button

class LinearCardActivity : LayoutDemoActivity(R.layout.activity_linear_card) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Button>(R.id.btn_agregar).setOnClickListener {
            avisar(getString(R.string.producto_agregado))
        }
    }
}
