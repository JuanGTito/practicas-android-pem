package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class Engine (val cylinders: Int, val fuel: String) {
    fun start () { Log.d(TAG, "$cylinders-cylinder engine started with $fuel") }
    fun stop () { Log.d(TAG, "Engine stopped") }
}
class CarC (val brand: String, val engine: Engine) {
    fun start () { Log.d(TAG, "$brand ready to go"); engine.start() }
    fun stop () { engine.stop(); Log.d(TAG, "$brand stopped") }
}
