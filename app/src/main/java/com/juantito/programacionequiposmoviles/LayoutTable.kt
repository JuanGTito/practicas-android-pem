package com.juantito.programacionequiposmoviles

import android.graphics.Typeface
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.text.NumberFormat
import java.util.Locale

internal fun monedaDeEjemplo(): NumberFormat =
    NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-PE"))

internal fun TableLayout.agregarFila(primera: String, segunda: String, tercera: String, destacada: Boolean = false) {
    val fila = TableRow(context)
    if (destacada) fila.setBackgroundColor(ContextCompat.getColor(context, R.color.layouts_surface))
    listOf(primera, segunda, tercera).forEachIndexed { columna, valor ->
        val celda = TextView(context, null, 0, R.style.Layouts_Cell).apply {
            text = valor
            gravity = if (columna == 2) Gravity.END else Gravity.START
            if (destacada) setTypeface(typeface, Typeface.BOLD)
        }
        fila.addView(celda)
    }
    addView(fila)
}
