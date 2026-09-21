package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class PersonCBI (val name: String) {
    var status: String = "Inactive"

    init {
        status = "Active"
        Log.d(TAG, "Welcome $name. Status initialized as: $status")
    }
}
