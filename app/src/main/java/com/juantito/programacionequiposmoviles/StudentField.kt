package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class StudentField {
    var name: String = ""
        get() = field.trim().replaceFirstChar { it.uppercase() }
        set(value) {
            if (value.isNotBlank()) field = value else Log.d(TAG, "Name cannot be empty")
        }

    var code: String = ""
        get() = field.uppercase()
        set(value) {
            if (value.startsWith("STU-")) field = value else Log.d(TAG, "Invalid code")
        }

    var average: Double = 0.0
        get() { Log.d(TAG, "Average consulted: $field"); return field }
        set(value) {
            if (value in 0.0..20.0) field = value else Log.d(TAG, "Average out of range: $value")
        }

    fun showInfo () {
        Log.d(TAG, "[$code] $name - Average: $average")
    }
}
