package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class Thermostat {
    var temperature: Double = 20.0
        get() {
            Log.d(TAG, "Temperature read: $field C")
            return field
        }
        set(value) {
            field = when {
                value < 15.0 -> { Log.d(TAG, "Too cold, adjusting to minimum"); 15.0 }
                value > 30.0 -> { Log.d(TAG, "Too hot, adjusting to maximum"); 30.0 }
                else -> value
            }
        }
}
