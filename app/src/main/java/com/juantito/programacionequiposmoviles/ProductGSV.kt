package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class ProductGSV {
    private var _name: String = ""
    private var _price: Double = 0.0

    fun getName () = _name
    fun setName (value: String) {
        if (value.isNotBlank()) _name = value.trim()
        else Log.d(TAG, "Name cannot be empty")
    }

    fun getPrice () = _price
    fun setPrice (value: Double) {
        if (value >= 0) _price = value
        else Log.d(TAG, "Price cannot be negative")
    }
}
