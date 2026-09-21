package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class UserManager {
    lateinit var userName: String
    lateinit var userEmail: String

    fun setup (name: String, email: String) {
        userName = name
        userEmail = email
        Log.d(TAG, "User configurated: $userName")
    }

    fun showInfo () {
        if (::userName.isInitialized) {
            Log.d(TAG, "Name $userName | Email $userEmail")
        } else {
            Log.d(TAG, "The user has not yet been configured")
        }
    }
}
