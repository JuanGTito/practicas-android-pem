package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class ReportL (val title: String) {
    private val data = mutableListOf<String>()
    fun add (item: String) = data.add(item)
    fun showWith (format: (String) -> String) {
        Log.d(TAG, "=== $title ==="); data.forEach { Log.d(TAG, format(it)) }
    }
    fun filter (condition: (String) -> Boolean) = data.filter { condition(it) }
}

fun processList (numbers: List<Int>, operation: (Int) -> Int) = numbers.map { operation(it) }
