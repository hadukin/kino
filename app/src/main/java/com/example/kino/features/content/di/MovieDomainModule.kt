package com.example.kino.features.content.di

import com.example.kino.features.content.domain.usecase.*
import org.koin.dsl.module

val movieDomainModule = module {
    factory { GetMoviePopularUseCase(contentRepository = get()) }
    factory { SaveToFavoriteUseCase(contentRepository = get()) }
    factory { DeleteFromFavoriteUseCase(contentRepository = get()) }
    factory { SaveAllMoviesUseCase(contentRepository = get()) }

    factory { ReadAllScheduleUseCase(repository = get()) }
    factory { CreateScheduleUseCase(repository = get()) }
}