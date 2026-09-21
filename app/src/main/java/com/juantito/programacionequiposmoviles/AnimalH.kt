package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class AnimalH (val name: String, val sound: String) {
    fun breathe () { Log.d(TAG, "$name is breathing") }
    fun makeSound () { Log.d(TAG, "$name says: $sound") }
}

class DogH (name: String) : AnimalH(name, "Woof") {
    fun fetchBall () { Log.d(TAG, "$name fetches the ball") }
}

class CatH (name: String) : AnimalH(name, "Meow") {
    fun climb () { Log.d(TAG, "$name is climbing") }
}
