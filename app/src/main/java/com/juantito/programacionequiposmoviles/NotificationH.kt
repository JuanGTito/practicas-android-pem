package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class NotificationH (val title: String, val message: String) {
    open fun show () { Log.d(TAG, "[$title] $message") }
    open fun register () { Log.d(TAG, "Registered: $title") }
}

class UrgentNotificationH (title: String, message: String, val level: Int) :
    NotificationH(title, message) {
    override fun show () { super.show(); Log.d(TAG, "Urgency level: $level") }
    override fun register () {
        super.register(); Log.d(TAG, "Urgency level $level saved in log")
    }
}

class SilentNotificationH (title: String, message: String) : NotificationH(title, message) {
    override fun show () { Log.d(TAG, "Silent notification: $title") }
}
