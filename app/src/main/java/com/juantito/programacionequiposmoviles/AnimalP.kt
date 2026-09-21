package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class AnimalP (val name: String) {
    open fun makeSound () { Log.d(TAG, "$name makes a generic sound") }
}

class DogP (name: String) : AnimalP(name) {
    override fun makeSound () { Log.d(TAG, "$name says: Woof!") }
}
class CatP (name: String) : AnimalP(name) {
    override fun makeSound () { Log.d(TAG, "$name says: Meow!") }
}
class CowP (name: String) : AnimalP(name) {
    override fun makeSound () { Log.d(TAG, "$name says: Moo!") }
}

fun processAnimals (animals: List<AnimalP>) {
    animals.forEach { it.makeSound() }
}
