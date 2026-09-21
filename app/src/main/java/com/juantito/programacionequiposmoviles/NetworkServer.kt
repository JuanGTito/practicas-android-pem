package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

interface ConnectionListener {
    fun onConnect (host: String)
    fun onDisconnect (host: String)
    fun onError (code: Int, message: String)
}
class NetworkServer (val host: String) {
    private var listener: ConnectionListener? = null
    private val defaultListener = object : ConnectionListener {
        override fun onConnect (host: String) { Log.d(TAG, "Connected to $host") }
        override fun onDisconnect (host: String) { Log.d(TAG, "Disconnected from $host") }
        override fun onError (code: Int, message: String) { Log.d(TAG, "Error [$code]: $message") }
    }
    fun setListener (listener: ConnectionListener) { this.listener = listener }
    fun connect () = (listener ?: defaultListener).onConnect(host)
    fun disconnect () = (listener ?: defaultListener).onDisconnect(host)
    fun simulateError (code: Int, message: String) = (listener ?: defaultListener).onError(code, message)
}
