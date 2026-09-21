package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class Calculator {
    fun add (a: Int, b: Int): Int {
        Log.d(TAG, "Integer sum: ${a + b}")
        return a + b
    }

    fun add (a: Double, b: Double): Double {
        Log.d(TAG, "Decimal sum: ${a + b}")
        return a + b
    }

    fun add (a: Int, b: Int, c: Int): Int {
        Log.d(TAG, "Three integer sum: ${a + b + c}")
        return a + b + c
    }

    fun add (values: List<Int>): Int {
        val result = values.sum()
        Log.d(TAG, "List sum: $result")
        return result
    }
}
