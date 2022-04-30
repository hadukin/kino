package com.example.kino.features.content.domain.usecase

import com.example.kino.features.content.data.models.Schedule
import com.example.kino.features.content.domain.repository.ScheduleRepository

class DeleteScheduleUseCase(private val repository: ScheduleRepository) {
    suspend fun execute(schedule: Schedule) {
        repository.deleteSchedule(schedule)
    }
}
