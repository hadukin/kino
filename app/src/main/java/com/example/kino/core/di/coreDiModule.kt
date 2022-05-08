package com.example.kino.core.di

import com.example.kino.services.ScheduleMovieReceiver
import com.example.kino.utils.NotificationHelper
import org.koin.dsl.module

val coreDiModule = module {
    single { NotificationHelper(context = get()) }
    single { ScheduleMovieReceiver() }
    // single { ScheduleReceiverHelper() }
}