package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class VehicleH (val brand: String) {
    open val type: String = "Vehicle"
    open val maximumSpeed: Int = 120
    fun showInfo () { Log.d(TAG, "$type $brand - Max speed: $maximumSpeed km/h") }
}

class SportsCar (brand: String, val turbo: Boolean) : VehicleH(brand) {
    override val type = "Sports car"
    override val maximumSpeed = 280
    fun activateTurbo () {
        if (turbo) Log.d(TAG, "$brand: Turbo activated!") else Log.d(TAG, "$brand: No turbo")
    }
}

class PickupTruck (brand: String, val loadCapacity: Double) : VehicleH(brand) {
    override val type = "Pickup truck"
    override val maximumSpeed = 140
    fun showLoad () { Log.d(TAG, "$brand can carry $loadCapacity tons") }
}
