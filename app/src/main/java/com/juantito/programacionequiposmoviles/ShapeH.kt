package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class ShapeH (val color: String) {
    open fun describe () { Log.d(TAG, "I am a $color shape") }
    open fun calculateArea () = 0.0
}

class RectangleH (color: String, val width: Double, val height: Double) : ShapeH(color) {
    override fun describe () {
        super.describe()
        Log.d(TAG, "Rectangle ${width}x$height")
    }
    override fun calculateArea () = width * height
}

class CircleH (color: String, val radius: Double) : ShapeH(color) {
    override fun describe () { Log.d(TAG, "Circle radius $radius and color $color") }
    override fun calculateArea () = Math.PI * radius * radius
}
