package com.juantito.programacionequiposmoviles

import android.os.Bundle

class LinearWeightActivity : LayoutDemoActivity(R.layout.activity_linear_weight) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        avisarBotones(R.id.btn_a, R.id.btn_b, R.id.btn_c)
    }
}
