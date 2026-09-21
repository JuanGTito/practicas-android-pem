package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class PersonField {
    var name: String = ""
        get() = field.uppercase()

    var age: Int = 0
        get() {
            Log.d(TAG, "Reading age: $field")
            return field
        }
}
