package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class PersonCP (val name: String, var age: Int) {
    fun info () {
        Log.d(TAG, "Person created: $name, Age: $age")
    }
}
