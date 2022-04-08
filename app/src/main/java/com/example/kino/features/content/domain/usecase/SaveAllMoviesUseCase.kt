package com.example.kino.features.content.domain.usecase


import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository

class SaveAllMoviesUseCase(private val contentRepository: ContentRepository) {
    suspend fun execute(items: List<Movie>) {
        return contentRepository.saveAllMovies(items)
    }
}