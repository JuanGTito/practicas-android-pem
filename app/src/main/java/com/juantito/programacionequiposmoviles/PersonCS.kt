package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class PersonCS (val name: String, var age: Int) {
    constructor(name: String) : this(name, 0) {
        Log.d(TAG, "Secondary constructor used for $name")
    }

    fun logData () {
        Log.d(TAG, "Name: $name | Age: $age")
    }
}
