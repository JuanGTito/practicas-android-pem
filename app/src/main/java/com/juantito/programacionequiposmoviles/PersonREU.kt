package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class PersonREU (val weight: Double, val height: Double) {
    fun getBMI () = weight / (height * height)
    fun checkStatus () {
        val bmi = getBMI()
        Log.d(TAG, "Analized of my IMC: $bmi")
    }
}