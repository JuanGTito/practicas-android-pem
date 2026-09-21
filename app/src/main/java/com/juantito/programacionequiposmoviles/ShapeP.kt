package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class ShapeP (val color: String) {
    open fun calculateArea () = 0.0
    open fun describe () = "Shape color $color"
}

class RectangleP (color: String, val width: Double, val height: Double) : ShapeP(color) {
    override fun calculateArea () = width * height
    override fun describe () = "Rectangle ${width}x$height ($color)"
}
class CircleP (color: String, val radius: Double) : ShapeP(color) {
    override fun calculateArea () = Math.PI * radius * radius
    override fun describe () = "Circle radius=$radius ($color)"
}
class TriangleP (color: String, val base: Double, val height: Double) : ShapeP(color) {
    override fun calculateArea () = (base * height) / 2
    override fun describe () = "Triangle base=$base height=$height ($color)"
}
class PentagonP (color: String, val side: Double) : ShapeP(color) {
    override fun calculateArea () = (side * side * Math.sqrt(25 + 10 * Math.sqrt(5.0))) / 4
    override fun describe () = "Pentagon side=$side ($color)"
}
class RhombusP (color: String, val diagonal1: Double, val diagonal2: Double) : ShapeP(color) {
    override fun calculateArea () = (diagonal1 * diagonal2) / 2
    override fun describe () = "Rhombus d1=$diagonal1 d2=$diagonal2 ($color)"
}

fun showShapeInfo (shape: ShapeP) {
    Log.d(TAG, shape.describe())
    Log.d(TAG, "Area: ${"%.2f".format(shape.calculateArea())}")
}
fun calculateTotalArea (shapes: List<ShapeP>) = shapes.sumOf { it.calculateArea() }
fun processShapes (shapes: List<ShapeP>) {
    shapes.forEach { Log.d(TAG, "${it.describe()} -> ${"%.2f".format(it.calculateArea())}") }
}
