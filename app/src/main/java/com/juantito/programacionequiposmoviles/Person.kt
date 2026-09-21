package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

// CLASS--------------------------------------------------------------------------------------------
// Seria un Objecto que es una instancia de clase, la clase es como el diseño y los objectos son como la construcion hecha.
class Person { // Declaramos una clase Person siempre creando tipo archivo como class
    var name: String = "" // Declaramos variable name dentro de class
    var age: Int = 0 // Declaramos varable age dentro de class
    var energy: Int = 200

    fun walk() { // Declaramos la funcion para llamar a las variables
        energy -= 10
        Log.d(TAG, "$name is walked $age") // imprimimos en Logcat
        Log.d(TAG, "$name walked, Energy Remaining $energy")
    }
}
