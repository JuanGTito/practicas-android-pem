package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class SavingsAccount (holder: String) : BankAccountE(holder) {
    fun deposit (amount: Double) {
        balance += amount
        registerMovement(amount, "Deposit")
    }

    fun withdraw (amount: Double) {
        if (amount <= balance) {
            balance -= amount
            registerMovement(amount, "Withdrawal")
        } else Log.d(TAG, "Insufficient balance")
    }
}
