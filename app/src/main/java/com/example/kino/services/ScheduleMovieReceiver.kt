package com.example.kino.services

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.kino.MainActivity
import com.example.kino.utils.NotificationHelper

class ScheduleMovieReceiver(private val notificationHelper: NotificationHelper) :
    BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val i = Intent(context, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)

        notificationHelper.notify("TITLE ScheduleMovieReceiver", "BODY ScheduleMovieReceiver")
    }
}