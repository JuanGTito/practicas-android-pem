package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class ReportGenerator {
    val header: String by lazy {
        Log.d(TAG, "Genering header ......")
        "=== Report Genered : ${System.currentTimeMillis()} ==="
    }

    fun PrintReport () {
        Log.d(TAG, header)
        Log.d(TAG, header)
    }
}