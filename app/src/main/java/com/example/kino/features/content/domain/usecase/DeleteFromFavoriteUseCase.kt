package com.example.kino.features.content.domain.usecase

import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository

class DeleteFromFavoriteUseCase(private val contentRepository: ContentRepository) {
    suspend fun execute(item: Movie) {
        return contentRepository.deleteFromFavorite(item)
    }
}