package com.example.kino.features.content.di

import com.example.kino.features.content.domain.usecase.DeleteFromFavoriteUseCase
import com.example.kino.features.content.domain.usecase.GetMoviePopularUseCase
import com.example.kino.features.content.domain.usecase.SaveAllMoviesUseCase
import com.example.kino.features.content.domain.usecase.SaveToFavoriteUseCase
import org.koin.dsl.module

val movieDomainModule = module {
    factory { GetMoviePopularUseCase(contentRepository = get()) }
    factory { SaveToFavoriteUseCase(contentRepository = get()) }
    factory { DeleteFromFavoriteUseCase(contentRepository = get()) }
    factory { SaveAllMoviesUseCase(contentRepository = get()) }
}