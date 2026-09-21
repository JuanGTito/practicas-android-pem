package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class PersonRE (val name: String, val birthYear: Int) {
    fun getAge (currentYear: Int): Int {
        val age = currentYear - birthYear
        Log.d(TAG, "procesing age of $name")
        return age
    }
}