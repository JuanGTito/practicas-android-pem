package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

open class Animal (val name: String) {
    protected val energy: Int = 100

    protected fun breathe (){
        Log.d(TAG, "$name is breathe")
    }

}