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
        val i = Intent(context, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)

        val bundle = intent?.extras
        var movieName: String? = null

        if (bundle != null) {

            movieName = bundle.getString("movie_name")

            // for (key in bundle.keySet()) {
            //     Log.e("TAG", "$key : ${bundle[key]}")
            // }
        }

        // intent?.extras?.keySet()?.map { it to intent?.extras?.get(it) }


        if (context != null) {
            val notificationHelper = NotificationHelper(context)
            notificationHelper.notify(
                "Вы хотели посмотреть фильм",
                "${movieName ?: ""}",
                pendingIntent
            )
        }
    }
}