package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class AccountSC (val owner: String, val accountType: String, var balance: Double) {
    constructor(owner: String) : this(owner, "Savings", 0.0) {
        Log.d(TAG, "Savings account created for $owner")
    }

    constructor(owner: String, accountType: String) : this(owner, accountType, 0.0) {
        Log.d(TAG, "$accountType account created for $owner")
    }

    fun logStatus () {
        Log.d(TAG, "Owner: $owner | Type: $accountType | Balance: $balance")
    }
}
