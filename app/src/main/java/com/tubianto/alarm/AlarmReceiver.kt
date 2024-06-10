package com.tubianto.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {
    private var alarmNotificationManager: NotificationManager? = null
    var NOTIFICATION_CHANNEL_ID = "KOM A USU"
    var NOTIFICATION_CHANNEL_NAME = "KOM A USU"
    private val NOTIFICATION_ID = 1

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        playAudio(context)
        sendNotification(context)
    }

    private fun playAudio(context: Context) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.bell)
        mediaPlayer.start()
    }

    //handle notification
    private fun sendNotification(context: Context) {
        val notifTitle = "Pengingat!"
        val notifContent = "Bell Masuk Pelajaran"
        alarmNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val newIntent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            context, 0,
            newIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        //cek jika OS android Oreo atau lebih baru
        //kalau tidak di set maka notifikasi tidak akan muncul di OS tersebut
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance
            )
            alarmNotificationManager!!.createNotificationChannel(mChannel)
        }

        //Buat notification
        val inboxStyle = NotificationCompat.BigTextStyle().bigText(notifContent)
        val notifBuilder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        notifBuilder.setContentTitle(notifTitle)
        notifBuilder.setSmallIcon(android.R.mipmap.sym_def_app_icon)
        notifBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
        notifBuilder.setContentText(notifContent)
        notifBuilder.setAutoCancel(true)
        notifBuilder.setStyle(inboxStyle)
        notifBuilder.setContentIntent(contentIntent)
        //Tampilkan notifikasi
        alarmNotificationManager!!.notify(NOTIFICATION_ID, notifBuilder.build())
    }
}