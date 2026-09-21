package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

interface OrderNotifier { fun send (message: String) }
class OrderEmail (val destination: String) : OrderNotifier {
    override fun send (message: String) { Log.d(TAG, "Email -> $destination: $message") }
}
class OrderSms (val phone: String) : OrderNotifier {
    override fun send (message: String) { Log.d(TAG, "SMS -> $phone: $message") }
}
class OrderService (val notifier: OrderNotifier) {
    fun confirmOrder (id: String) {
        Log.d(TAG, "Order $id confirmed"); notifier.send("Your order $id was confirmed")
    }
    fun cancelOrder (id: String) {
        Log.d(TAG, "Order $id canceled"); notifier.send("Your order $id was canceled")
    }
}
