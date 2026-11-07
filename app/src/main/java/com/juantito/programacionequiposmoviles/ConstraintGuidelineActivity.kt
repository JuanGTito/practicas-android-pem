package com.juantito.programacionequiposmoviles

import android.os.Bundle

class ConstraintGuidelineActivity : LayoutDemoActivity(R.layout.activity_constraint_guideline) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        avisarBotones(R.id.btn_inicio_guia, R.id.btn_fin_guia)
    }
}
