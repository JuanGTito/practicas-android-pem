package com.juantito.programacionequiposmoviles

import android.os.Bundle

class ConstraintChainActivity : LayoutDemoActivity(R.layout.activity_constraint_chain) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        avisarBotones(R.id.btn_a, R.id.btn_b, R.id.btn_c)
    }
}
