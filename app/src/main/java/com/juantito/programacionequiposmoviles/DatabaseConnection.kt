package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG
import java.io.Closeable

class DatabaseConnection (val name: String) : Closeable {
    init {
        Log.d(TAG, "Database connected: $name")
    }

    fun query (query: String): String {
        Log.d(TAG, "Executing: $query")
        return "Result of: $query"
    }

    override fun close () {
        Log.d(TAG, "Database disconnected: $name")
    }
}
