package com.example.kino.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavDeepLinkBuilder
import com.example.kino.R
import com.example.kino.features.content.domain.usecase.DeleteScheduleUseCase
import com.example.kino.features.content.domain.usecase.GetMovieByIdUseCase
import com.example.kino.features.content.domain.usecase.ReadAllScheduleUseCase
import com.example.kino.features.content.presentation.home.details.ContentDetailFragment
import com.example.kino.utils.NotificationHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class ScheduleMovieReceiver : BroadcastReceiver(), KoinComponent {
    private val readAllScheduleUseCase: ReadAllScheduleUseCase by inject()
    private val deleteScheduleUseCase: DeleteScheduleUseCase by inject()
    private val getMovieByIdUseCase: GetMovieByIdUseCase by inject()


    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationHelper = context?.let { NotificationHelper(it) }

        CoroutineScope(Dispatchers.IO).launch {
            val resultDeferred = async { readAllScheduleUseCase.execute() }
            val result = resultDeferred.await()

            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            for (item in result) {
                if (item.hourOfDay == hour && item.minute == minute) {
                    val resultMovieDeferred = async { getMovieByIdUseCase.execute(item.filmId) }
                    val resultMovie = resultMovieDeferred.await()

                    val args = Bundle()
                    args.putParcelable(ContentDetailFragment.CONTENT, resultMovie)

                    val pendingIntent = NavDeepLinkBuilder(context!!)
                        .setGraph(R.navigation.bottom_navigation)
                        .setDestination(R.id.contentDetailFragment)
                        .setArguments(args)
                        .createPendingIntent()

                    notificationHelper?.notify(
                        title = "Не забудьте посмотреть фильм",
                        body = item.title,
                        pendingIntent = pendingIntent,
                    )

                    val resultDeferred = async { deleteScheduleUseCase.execute(item) }
                    val result = resultDeferred.await()
                }
            }
        }
    }
}