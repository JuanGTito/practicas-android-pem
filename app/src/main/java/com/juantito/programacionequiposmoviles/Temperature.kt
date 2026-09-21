package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class Temperature {
    private var _celsius: Double = 0.0

    var celsius: Double
        get() = _celsius
        set(value) {
            if (value >= -273.15) _celsius = value
            else Log.d(TAG, "Invalid temperature: $value")
        }

    val fahrenheit: Double
        get() = _celsius * 9 / 5 + 32
}
