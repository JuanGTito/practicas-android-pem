package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class Car (val brand: String, var color: String) {
    fun drive () {
        Log.d(TAG, "Driving a $brand of color $color")
    }
}