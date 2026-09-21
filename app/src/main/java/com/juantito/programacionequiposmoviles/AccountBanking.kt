package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class AccountBanking (val holder: String) {
    private var balance: Double = 0.0

    private fun validatedAmount (amount: Double): Boolean {
        return amount > 0
    }

    fun deposit (amount: Double) {
        if (validatedAmount(amount)) {
            balance += amount
            Log.d(TAG, "New balance: $balance")
        }
    }

    fun consultBalance(): Double {
        return balance
    }
}