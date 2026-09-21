package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class PersonSVD (
    val name: String,
    val age: Int = 0,
    val email: String = "no email"
) {
    fun logData () {
        Log.d(TAG, "Name: $name | Age: $age | Email: $email")
    }
}
