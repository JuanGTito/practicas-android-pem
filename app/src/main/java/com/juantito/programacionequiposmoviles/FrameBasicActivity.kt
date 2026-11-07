package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.isVisible

class FrameBasicActivity : LayoutDemoActivity(R.layout.activity_frame_basic) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val etiqueta = findViewById<View>(R.id.txt_capa)
        findViewById<Button>(R.id.btn_capa).setOnClickListener {
            etiqueta.isVisible = !etiqueta.isVisible
            avisar(getString(R.string.alternar_capa))
        }
    }
}
