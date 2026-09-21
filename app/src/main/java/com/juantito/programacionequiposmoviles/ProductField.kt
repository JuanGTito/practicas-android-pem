package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class ProductField {
    var price: Double = 0.0
        set(value) {
            if (value >= 0) {
                field = value
                Log.d(TAG, "Price updated: $field")
            } else Log.d(TAG, "Invalid price: $value")
        }

    var name: String = ""
        set(value) { field = value.trim().replaceFirstChar { it.uppercase() } }
}
