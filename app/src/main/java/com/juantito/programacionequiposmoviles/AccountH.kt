package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class AccountH (val holder: String) {
    protected var balance: Double = 0.0
    final fun consultBalance () = balance
    open fun deposit (amount: Double) {
        balance += amount
        Log.d(TAG, "Deposit: $amount | Balance: $balance")
    }
}

class PremiumAccount (holder: String) : AccountH(holder) {
    override fun deposit (amount: Double) {
        val bonus = amount * 0.05
        balance += amount + bonus
        Log.d(TAG, "Deposit: $amount + bonus $bonus | Balance: $balance")
    }
}
