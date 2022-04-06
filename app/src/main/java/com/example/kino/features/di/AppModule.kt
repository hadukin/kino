package com.example.kino.features.di

import com.example.kino.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(getMoviePopularUseCase = get()) }
}