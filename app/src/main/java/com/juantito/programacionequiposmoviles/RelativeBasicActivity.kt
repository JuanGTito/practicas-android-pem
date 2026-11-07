package com.juantito.programacionequiposmoviles

import android.os.Bundle

class RelativeBasicActivity : LayoutDemoActivity(R.layout.activity_relative_basic) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        avisarBotones(R.id.btn_editar)
    }
}
