package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class ButtonL (val label: String) {
    var onClick: () -> Unit = { Log.d(TAG, "Click on '$label'") }
    var onValidate: (String) -> Boolean = { text -> text.isNotBlank() }
    fun click () = onClick()
    fun validate (text: String) = onValidate(text)
}
