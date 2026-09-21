package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

interface Swimmer { fun swim () { Log.d(TAG, "Swimming") } }
interface Flyer { fun fly () { Log.d(TAG, "Flying") } }
interface Runner { fun run () { Log.d(TAG, "Running") } }

class Duck (val name: String) : Swimmer, Flyer, Runner {
    override fun swim () { Log.d(TAG, "$name swims") }
    override fun fly () { Log.d(TAG, "$name flies") }
}

interface GreetingA { fun greet () { Log.d(TAG, "Hello from A") } }
interface GreetingB { fun greet () { Log.d(TAG, "Hello from B") } }

class DoubleGreeting : GreetingA, GreetingB {
    override fun greet () {
        super<GreetingA>.greet()
        super<GreetingB>.greet()
    }
}
