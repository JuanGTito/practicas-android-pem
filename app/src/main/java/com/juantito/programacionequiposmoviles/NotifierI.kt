package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

interface NotifierI { fun send (message: String) }
class NotifierEmailI (val destination: String) : NotifierI {
    override fun send (message: String) { Log.d(TAG, "Email -> $destination: $message") }
}
class NotifierSmsI (val phone: String) : NotifierI {
    override fun send (message: String) { Log.d(TAG, "SMS -> $phone: $message") }
}
class NotifierPushI (val token: String) : NotifierI {
    override fun send (message: String) { Log.d(TAG, "Push -> $token: $message") }
}
fun alert (channels: List<NotifierI>, message: String) { channels.forEach { it.send(message) } }
