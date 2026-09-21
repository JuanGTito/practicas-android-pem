package com.juantito.programacionequiposmoviles

class CircleField (val radius: Double) {
    val area: Double
        get() = Math.PI * radius * radius
    val perimeter: Double
        get() = 2 * Math.PI * radius
}
