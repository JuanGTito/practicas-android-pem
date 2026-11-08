package com.juantito.programacionequiposmoviles

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

internal class AirplaneModeReceiver(private val alCambiar: (Boolean) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_AIRPLANE_MODE_CHANGED) return
        val activo = intent.getBooleanExtra("state", false)
        alCambiar(activo)
        Log.d(TAG_COMPONENTES, "ACTION_AIRPLANE_MODE_CHANGED: $activo")
        Toast.makeText(
            context,
            if (activo) R.string.modo_avion_activo else R.string.modo_avion_inactivo,
            Toast.LENGTH_SHORT
        ).show()
    }
}
