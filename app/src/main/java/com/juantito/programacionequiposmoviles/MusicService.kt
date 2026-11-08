package com.juantito.programacionequiposmoviles

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.util.Log

class MusicService : Service() {
    private var reproductor: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == DETENER) {
            stopSelf()
            return START_NOT_STICKY
        }
        val aviso = crearNotificacion()
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(NOTIFICACION_ID, aviso, ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK)
        } else {
            startForeground(NOTIFICACION_ID, aviso)
        }
        if (reproductor == null) prepararAudio()
        return START_NOT_STICKY
    }

    private fun prepararAudio() {
        val atributos = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
        val tono = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        val player = MediaPlayer.create(this, tono, null, atributos, 0)
        if (player == null) {
            Log.w(TAG_COMPONENTES, "No se pudo abrir el tono de demostración")
            stopSelf()
            return
        }
        reproductor = player
        player.isLooping = true
        player.setOnErrorListener { _, _, _ ->
            Log.w(TAG_COMPONENTES, "Error al reproducir el tono")
            stopSelf()
            true
        }
        player.start()
        Log.d(TAG_COMPONENTES, "MusicService: reproducción iniciada")
    }

    @Suppress("DEPRECATION")
    private fun crearNotificacion(): Notification {
        val builder = if (Build.VERSION.SDK_INT >= 26) {
            getSystemService(NotificationManager::class.java).createNotificationChannel(
                NotificationChannel(CANAL, getString(R.string.audio_canal), NotificationManager.IMPORTANCE_LOW)
            )
            Notification.Builder(this, CANAL)
        } else {
            Notification.Builder(this)
        }
        val abrir = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_IMMUTABLE
        )
        val detener = PendingIntent.getService(
            this, 0, Intent(this, MusicService::class.java).setAction(DETENER), PendingIntent.FLAG_IMMUTABLE
        )
        return builder.setSmallIcon(android.R.drawable.ic_media_play)
            .setContentTitle(getString(R.string.audio_canal))
            .setContentText(getString(R.string.audio_reproduciendo))
            .setContentIntent(abrir)
            .setOngoing(true)
            .addAction(Notification.Action.Builder(null, getString(R.string.detener_audio), detener).build())
            .build()
    }

    override fun onDestroy() {
        reproductor?.release()
        reproductor = null
        stopForeground(STOP_FOREGROUND_REMOVE)
        Log.d(TAG_COMPONENTES, "MusicService: reproducción detenida")
        super.onDestroy()
    }

    private companion object {
        const val CANAL = "audio_practica"
        const val NOTIFICACION_ID = 6
        const val DETENER = "com.juantito.programacionequiposmoviles.DETENER_AUDIO"
    }
}
