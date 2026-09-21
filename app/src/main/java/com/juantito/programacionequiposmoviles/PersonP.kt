package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class PersonP (val name: String) {
    public val id: Int = 1
    val age: Int = 24

    public fun Greet () {
        Log.d(TAG, "I am $name")
    }
}