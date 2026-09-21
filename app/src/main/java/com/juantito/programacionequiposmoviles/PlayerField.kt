package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class PlayerField (name: String) {
    var name: String = name
        get() = field.uppercase()
        private set
    var points: Int = 0
        get() { Log.d(TAG, "Points consulted: $field"); return field }
        private set
    var level: Int = 1
        private set

    fun score (amount: Int) {
        if (amount > 0) {
            points += amount
            if (points >= level * 100) {
                level++
                Log.d(TAG, "New level: $level")
            }
        }
    }
}
