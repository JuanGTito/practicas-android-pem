package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class LegacyConnection (val host: String) {
    init {
        Log.d(TAG, "Connection opened with: $host")
    }

    protected fun finalize () {
        Log.d(TAG, "Connection closed with: $host")
    }
}
