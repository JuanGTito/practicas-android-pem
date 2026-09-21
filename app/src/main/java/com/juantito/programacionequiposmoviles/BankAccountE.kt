package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class BankAccountE (val holder: String) {
    protected var balance: Double = 0.0

    protected fun registerMovement (amount: Double, type: String) {
        Log.d(TAG, "$type: $amount | Balance: $balance")
    }

    fun consultBalance () = balance
}
