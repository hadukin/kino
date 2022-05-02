package com.example.kino.features.content.di

import com.example.kino.MainViewModel
import com.example.kino.features.content.domain.usecase.CreateScheduleUseCase
import com.example.kino.features.content.domain.usecase.DeleteScheduleUseCase
import com.example.kino.features.content.domain.usecase.GetScheduleByIdUseCase
import com.example.kino.features.content.presentation.home.details.ContentDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    viewModel {
        MainViewModel(
            context = get(),
            getMoviePopularUseCase = get(),
            saveToFavoriteUseCase = get(),
            deleteFromFavoriteUseCase = get(),
            createScheduleUseCase = get(),
        )
    }

    viewModel {
        ContentDetailViewModel(
            createScheduleUseCase = get(),
            getScheduleByIdUseCase = get(),
            deleteScheduleUseCase = get(),
        )
    }
}