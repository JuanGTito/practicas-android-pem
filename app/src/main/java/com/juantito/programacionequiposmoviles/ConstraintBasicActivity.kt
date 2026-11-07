package com.juantito.programacionequiposmoviles

import android.os.Bundle

class ConstraintBasicActivity : LayoutDemoActivity(R.layout.activity_constraint_basic) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        avisarBotones(R.id.btn_centrado)
    }
}
