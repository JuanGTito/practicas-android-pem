package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

abstract class NotificationA {
    val title: String
    val message: String
    val priority: Int
    constructor(title: String, message: String) {
        this.title = title; this.message = message; this.priority = 1
    }
    constructor(title: String, message: String, priority: Int) {
        this.title = title; this.message = message; this.priority = priority
    }
    abstract fun send ()
    fun register () { Log.d(TAG, "[$priority] Notification registered: $title") }
}

class NotificationEmail (title: String, message: String, val destination: String) :
    NotificationA(title, message) {
    override fun send () { Log.d(TAG, "Email -> $destination: $title - $message") }
}
class NotificationUrgent (title: String, message: String) :
    NotificationA(title, message, 3) {
    override fun send () { Log.d(TAG, "URGENT: $title - $message") }
}
