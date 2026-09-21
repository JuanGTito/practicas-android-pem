package com.juantito.programacionequiposmoviles

data class Vector (val x: Double, val y: Double) {
    operator fun plus (other: Vector) = Vector(x + other.x, y + other.y)
    operator fun minus (other: Vector) = Vector(x - other.x, y - other.y)
    operator fun times (scale: Double) = Vector(x * scale, y * scale)
    fun magnitude () = Math.sqrt(x * x + y * y)

    override fun toString(): String {
        return "Vector(${"%.2f".format(x)}, ${"%.2f".format(y)})"
    }
}
