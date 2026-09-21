package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class VehicleBI (val brand: String, val model: String, val year: Int = 2024) {
    val id: String

    init {
        id = "${brand.take(3).uppercase()}-$year"
        Log.d(TAG, "Vehicle registered with ID: $id")
    }

    constructor(brand: String, model: String) : this(brand, model, 2024) {
        Log.d(TAG, "Secondary constructor: default year assigned")
    }

    fun logInfo () {
        Log.d(TAG, "ID: $id | $brand $model ($year)")
    }
}
