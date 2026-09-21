package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class Account {
    var balance: Double = 0.0

    fun deposit (amount: Double) {
        balance += amount
        Log.d(TAG, "Depisit of $amount, Your Balance is: $balance")
    }

    fun checkStatus () {
        if (balance > 0) {
            Log.d(TAG, "Account active with balance positive")
        } else {
            Log.d(TAG, "Account without balance")
        }

    }
}
