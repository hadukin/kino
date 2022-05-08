package com.example.kino.features.content.domain.usecase

import com.example.kino.features.content.data.models.Schedule
import com.example.kino.features.content.domain.repository.ScheduleRepository

class GetScheduleByIdUseCase(private val repository: ScheduleRepository) {
    suspend fun execute(filmId: Int): Schedule {
        return repository.getScheduleById(filmId)
    }
}
