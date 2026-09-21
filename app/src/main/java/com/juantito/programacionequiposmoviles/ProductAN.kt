package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class ProductAN (
    val name: String,
    val price: Double = 0.0,
    val stock: Int = 0,
    val category: String = "General"
) {
    fun logInfo () {
        Log.d(TAG, "[$category] $name - S/.$price (stock: $stock)")
    }
}
