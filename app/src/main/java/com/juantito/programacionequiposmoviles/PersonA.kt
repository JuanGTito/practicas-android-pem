package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class PersonA {
    var name: String = "" // Declaramos variable name dentro de class
    var energy: Int = 200

    fun walk() { // Declaramos la funcion para llamar a las variables
        energy -= 10
        Log.d(TAG, "$name walked, Energy Remaining $energy")
    }
}