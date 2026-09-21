package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class LivingBeing (val name: String) {
    open fun describe () { Log.d(TAG, "I am $name") }
}

open class AnimalHM (name: String, val species: String) : LivingBeing(name) {
    override fun describe () { super.describe(); Log.d(TAG, "Species: $species") }
    open fun feed () { Log.d(TAG, "$name feeds") }
}

class Wolf (name: String) : AnimalHM(name, "Canis lupus") {
    override fun describe () {
        super.describe(); Log.d(TAG, "I am a wolf and hunt in a pack")
    }
    override fun feed () { Log.d(TAG, "$name hunts its prey") }
    fun howl () { Log.d(TAG, "$name howls at the moon") }
}
