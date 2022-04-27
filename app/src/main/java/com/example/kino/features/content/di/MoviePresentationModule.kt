package com.example.kino.features.content.di

import com.example.kino.features.content.presentation.home.HomeFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val moviePresentationModule = module {
    fragment<HomeFragment> { HomeFragment(notificationHelper = get()) }
}