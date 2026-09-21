package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class UserSession (val userId: String) : AutoCloseable {
    init {
        Log.d(TAG, "Session started for: $userId")
    }

    fun obtainData () = "User data: $userId"

    override fun close () {
        Log.d(TAG, "Session closed for: $userId")
    }
}
