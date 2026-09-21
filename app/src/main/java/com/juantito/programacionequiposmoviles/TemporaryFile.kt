package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG
import java.io.Closeable

class TemporaryFile (val name: String) : Closeable {
    init {
        Log.d(TAG, "File created: $name")
    }

    fun write (content: String) {
        Log.d(TAG, "Writing to $name: $content")
    }

    override fun close () {
        Log.d(TAG, "File closed and resources released: $name")
    }
}
