package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class StudentGS {
    private var _name: String = ""
    private var _code: String = ""
    private var _average: Double = 0.0

    fun getName () = _name
    fun setName (value: String) { _name = value.trim() }
    fun getCode () = _code
    fun setCode (value: String) {
        if (value.startsWith("STU-")) _code = value
        else Log.d(TAG, "Invalid code: it must start with 'STU-'")
    }
    fun getAverage () = _average
    fun setAverage (value: Double) {
        if (value in 0.0..20.0) _average = value
        else Log.d(TAG, "Average out of range: $value")
    }

    fun showInfo () {
        Log.d(TAG, "[$_code] $_name - Average: $_average")
    }
}
