package com.example.kino.features.content.domain.usecase

import com.example.kino.features.content.domain.repository.ScheduleRepository

class ReadAllScheduleUseCase(private val repository: ScheduleRepository) {
    suspend fun execute() {
        return repository.readAllSchedule()
    }
}

