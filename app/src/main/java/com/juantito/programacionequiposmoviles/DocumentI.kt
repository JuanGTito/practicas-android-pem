package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

interface Printable { fun print () }
interface Shareable { fun share (destination: String) }
interface Downloadable { val downloadUrl: String; fun download () }

class DocumentI (val title: String, override val downloadUrl: String) :
    Printable, Shareable, Downloadable {
    override fun print () { Log.d(TAG, "Printing: $title") }
    override fun share (destination: String) { Log.d(TAG, "Sharing '$title' with $destination") }
    override fun download () { Log.d(TAG, "Downloading '$title' from $downloadUrl") }
}
class ImageI (val name: String, override val downloadUrl: String) : Shareable, Downloadable {
    override fun share (destination: String) { Log.d(TAG, "Sharing image '$name' with $destination") }
    override fun download () { Log.d(TAG, "Downloading image '$name' from $downloadUrl") }
}
