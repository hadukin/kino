package com.example.kino.features.content.domain.usecase

import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository

class GetMovieByIdUseCase(private val contentRepository: ContentRepository) {
    suspend fun execute(id: Int): Movie {
        return contentRepository.getMovieById(id)
    }
}
