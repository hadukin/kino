package com.example.kino.features.di

import com.example.kino.features.content.domain.usecase.GetMoviePopularUseCase
import org.koin.dsl.factory
import org.koin.dsl.module

val domainModule = module {
    factory { GetMoviePopularUseCase(contentRepository = get()) }
}