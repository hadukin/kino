package com.example.kino.features.content.domain.usecase

import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository

class GetMoviePopularUseCase(private val contentRepository: ContentRepository) {

    suspend fun execute(page: Int): ArrayList<Movie> {
        return contentRepository.getMoviePopular(page) ?: arrayListOf()
    }
}