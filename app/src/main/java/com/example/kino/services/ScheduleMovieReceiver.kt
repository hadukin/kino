package com.example.kino.services

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.kino.MainActivity
import com.example.kino.utils.NotificationHelper

class ScheduleMovieReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("calendar_receiver", "1111")
        val i = Intent(context, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)
        val notificationHelper = NotificationHelper(context!!)
        notificationHelper.notify("TITLE ScheduleMovieReceiver", "BODY ScheduleMovieReceiver")
    }
}