package com.example.kino.core.di

import com.example.kino.features.content.presentation.home.HomeFragment
import com.example.kino.utils.NotificationHelper
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val coreDiModule = module {
    single<NotificationHelper> { NotificationHelper(context = get()) }
}