package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class Counter {
    var value: Int = 0
        private set

    fun increment () {
        value++
        Log.d(TAG, "Counter: $value")
    }

    fun reset () {
        value = 0
        Log.d(TAG, "Counter reset")
    }
}
