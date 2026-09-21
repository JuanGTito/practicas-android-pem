package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class ProductH {
    val name: String
    val price: Double
    val category: String

    constructor(name: String, price: Double) {
        this.name = name; this.price = price; this.category = "General"
    }
    constructor(name: String, price: Double, category: String) {
        this.name = name; this.price = price; this.category = category
    }
    fun showInfo () { Log.d(TAG, "[$category] $name - S/ $price") }
}

class DigitalProduct (name: String, price: Double, val downloadUrl: String) :
    ProductH(name, price, "Digital") {
    fun download () { Log.d(TAG, "Downloading $name from: $downloadUrl") }
}

class PhysicalProduct (name: String, price: Double, val weightKg: Double) :
    ProductH(name, price, "Physical") {
    fun calculateShipping () = weightKg * 5.0
}
