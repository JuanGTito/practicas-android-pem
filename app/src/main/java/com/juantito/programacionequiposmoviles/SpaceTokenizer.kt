package com.juantito.programacionequiposmoviles

import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.widget.MultiAutoCompleteTextView

internal class SpaceTokenizer : MultiAutoCompleteTextView.Tokenizer {
    override fun findTokenStart(text: CharSequence, cursor: Int): Int {
        var inicio = cursor
        while (inicio > 0 && !text[inicio - 1].isWhitespace()) inicio--
        return inicio
    }

    override fun findTokenEnd(text: CharSequence, cursor: Int): Int {
        var fin = cursor
        while (fin < text.length && !text[fin].isWhitespace()) fin++
        return fin
    }

    override fun terminateToken(text: CharSequence): CharSequence {
        if (text.isEmpty() || text.last().isWhitespace()) return text
        if (text !is Spanned) return "$text "
        return SpannableString("$text ").also {
            TextUtils.copySpansFrom(text, 0, text.length, Any::class.java, it, 0)
        }
    }
}
