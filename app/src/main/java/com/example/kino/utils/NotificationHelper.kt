package com.example.kino.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.kino.R

class NotificationHelper(private val context: Context) {
    private var notificationId = 1
    private val manager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        createChannel()
    }

    companion object {
        const val channelId = "movie.app"
        const val channelName = "movie"
        const val channelDescription = "Best movie app"
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            manager.createNotificationChannel(channel)
        }
    }

    private fun notificationBuilder(
        title: String,
        body: String,
        pendingIntent: PendingIntent,
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_baseline_arrow_back_24)
            .setChannelId(channelId)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent)
    }

    fun notify(
        title: String,
        body: String,
        pendingIntent: PendingIntent,
    ) {
        manager.notify(
            notificationId++,
            notificationBuilder(title, body, pendingIntent).build()
        )
    }
}