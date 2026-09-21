package com.juantito.programacionequiposmoviles

interface AnimationL {
    fun start ()
    fun finish ()
}

fun interface TransformerL {
    fun transform (value: Int): Int
}
