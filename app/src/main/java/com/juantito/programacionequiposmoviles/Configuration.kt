package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class Configuration {
    internal val apiKey: String = "abc-123"
    internal val timeOut: Int = 5000

    internal fun reset () {
        Log.d(TAG, "Configuration reset")
    }

}