package com.example.kino.features.content.di

import com.example.kino.features.content.domain.usecase.GetMoviePopularUseCase
import org.koin.dsl.module

val movieDomainModule = module {
    factory { GetMoviePopularUseCase(contentRepository = get()) }
}