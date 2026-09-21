package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

abstract class PaymentProcess (val amount: Double) {
    abstract fun validate (): Boolean
    abstract fun execute (): String
    abstract fun register ()
    fun process () {
        Log.d(TAG, "Starting payment of S/ $amount")
        if (validate()) {
            Log.d(TAG, execute()); register(); Log.d(TAG, "Payment completed")
        } else Log.d(TAG, "Payment rejected: validation failed")
    }
}

class CardPayment (amount: Double, val lastFour: String, val funds: Double) : PaymentProcess(amount) {
    override fun validate () = funds >= amount
    override fun execute () = "Charged S/ $amount to card ****$lastFour"
    override fun register () { Log.d(TAG, "Card transaction registered") }
}
class TransferPayment (amount: Double, val bank: String, val validAccount: Boolean) : PaymentProcess(amount) {
    override fun validate () = validAccount
    override fun execute () = "Transferred S/ $amount through $bank"
    override fun register () { Log.d(TAG, "Transfer registered") }
}
