package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

interface LoggerI { fun log (message: String) { Log.d(TAG, "Logger -> $message") } }
interface MonitorI { fun log (message: String) { Log.d(TAG, "Monitor -> $message") } }
class SystemI : LoggerI, MonitorI {
    override fun log (message: String) {
        super<LoggerI>.log(message); super<MonitorI>.log(message)
    }
}
class SystemSimpleI : LoggerI, MonitorI {
    override fun log (message: String) { super<LoggerI>.log(message) }
}
