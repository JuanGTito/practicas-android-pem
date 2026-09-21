package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class Printer (val model: String) {
    fun print (content: String) { Log.d(TAG, "[$model] Printing: $content") }
}
class DocumentC (val title: String, val content: String) {
    fun show () { Log.d(TAG, "Document: $title\n$content") }
}
class PrintOffice (val printer: Printer) {
    fun processDocument (document: DocumentC) {
        Log.d(TAG, "Processing print request...")
        document.show(); printer.print(document.content)
        Log.d(TAG, "Printing completed")
    }
}
