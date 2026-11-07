package com.juantito.programacionequiposmoviles

import android.os.Bundle

class LinearBasicActivity : LayoutDemoActivity(R.layout.activity_linear_basic) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        avisarBotones(R.id.btn_enviar, R.id.btn_opcion)
    }
}
