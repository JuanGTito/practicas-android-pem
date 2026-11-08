package com.juantito.programacionequiposmoviles

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class MiWidgetJuliaca : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        val abrir = PendingIntent.getActivity(
            context, 0, Intent(context, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE
        )
        ids.forEach { id ->
            val vistas = RemoteViews(context.packageName, R.layout.mi_widget_juliaca)
            vistas.setTextViewText(R.id.appwidget_text, context.getString(R.string.widget_texto))
            vistas.setOnClickPendingIntent(R.id.appwidget_text, abrir)
            manager.updateAppWidget(id, vistas)
        }
    }
}
