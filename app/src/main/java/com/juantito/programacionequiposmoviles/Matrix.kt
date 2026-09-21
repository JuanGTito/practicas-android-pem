package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class Matrix (val rows: Int, val columns: Int) {
    private val data = Array(rows) { DoubleArray(columns) }

    operator fun get (row: Int, column: Int): Double = data[row][column]

    operator fun set (row: Int, column: Int, value: Double) {
        data[row][column] = value
    }

    fun show () {
        for (row in data) {
            Log.d(TAG, row.joinToString(" | ") { "%.1f".format(it) })
        }
    }
}
