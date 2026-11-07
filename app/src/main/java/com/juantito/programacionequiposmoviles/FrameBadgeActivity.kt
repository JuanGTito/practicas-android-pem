package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.isVisible

class FrameBadgeActivity : LayoutDemoActivity(R.layout.activity_frame_badge) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val etiqueta = findViewById<View>(R.id.badge_oferta)
        findViewById<Button>(R.id.btn_oferta).setOnClickListener {
            etiqueta.isVisible = !etiqueta.isVisible
            avisar(getString(R.string.alternar_oferta))
        }
    }
}
