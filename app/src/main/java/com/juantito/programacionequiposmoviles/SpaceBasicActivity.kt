package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.widget.Button

class SpaceBasicActivity : LayoutDemoActivity(R.layout.activity_space_basic) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Button>(R.id.btn_atras).setOnClickListener { finish() }
        avisarBotones(R.id.btn_final)
    }
}
