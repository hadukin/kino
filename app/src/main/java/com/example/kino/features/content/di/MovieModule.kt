package com.example.kino.features.content.di

import com.example.kino.MainViewModel
import org.koin.dsl.module

val movieModule = module {
    // viewModel { MainViewModel(getMoviePopularUseCase = get()) }
    single {
        MainViewModel(
            getMoviePopularUseCase = get(),
            saveToFavoriteUseCase = get(),
            deleteFromFavoriteUseCase = get()
        )
    }
}