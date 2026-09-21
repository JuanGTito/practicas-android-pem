package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class VehicleC (val brand: String) {
    fun showBrand () { Log.d(TAG, "Brand: $brand") }
}
class Motorcycle (brand: String) : VehicleC(brand)
class Battery (val capacity: Int) {
    fun charge () { Log.d(TAG, "${capacity}mAh battery charging") }
    fun obtainLevel () = 85
}
class Gps (val provider: String) {
    fun obtainLocation () = "Lat: -16.4, Lon: -71.5"
}
class SmartWatch (val model: String, val battery: Battery, val gps: Gps) {
    fun showInfo () {
        Log.d(TAG, "Watch: $model")
        Log.d(TAG, "Battery: ${battery.obtainLevel()}%")
        Log.d(TAG, "Location: ${gps.obtainLocation()}")
    }
    fun chargeDevice () { battery.charge() }
}
