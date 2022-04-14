package com.example.kino.features.content.di

import com.example.kino.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    // viewModel {
    //     MainViewModel(
    //         context = get(),
    //         getMoviePopularUseCase = get(),
    //         saveToFavoriteUseCase = get(),
    //         deleteFromFavoriteUseCase = get()
    //     )
    // }
    single {
        MainViewModel(
            context = get(),
            getMoviePopularUseCase = get(),
            saveToFavoriteUseCase = get(),
            deleteFromFavoriteUseCase = get()
        )
    }
}