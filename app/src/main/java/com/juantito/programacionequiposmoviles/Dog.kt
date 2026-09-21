package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class Dog (name: String): Animal(name) {
    fun bark () {
        Log.d(TAG, "$name have energy: $energy")
        breathe()
    }
}