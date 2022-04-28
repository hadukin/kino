package com.example.kino.features.content.domain.usecase

import com.example.kino.features.content.data.models.Schedule
import com.example.kino.features.content.domain.repository.ScheduleRepository

class ReadAllScheduleUseCase(private val repository: ScheduleRepository) {
    suspend fun execute(): List<Schedule> {
        return repository.readAllSchedule()
    }
}

