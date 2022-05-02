package com.example.kino.features.content.presentation.home.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kino.features.content.data.models.Schedule
import com.example.kino.features.content.domain.usecase.CreateScheduleUseCase
import com.example.kino.features.content.domain.usecase.DeleteScheduleUseCase
import com.example.kino.features.content.domain.usecase.GetScheduleByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class ContentDetailViewModel(
    private val createScheduleUseCase: CreateScheduleUseCase,
    private val getScheduleByIdUseCase: GetScheduleByIdUseCase,
    private val deleteScheduleUseCase: DeleteScheduleUseCase,
) : ViewModel() {

    private val _schedule = MutableLiveData<Schedule?>()
    val schedule: LiveData<Schedule?> = _schedule

    suspend fun deleteSchedule(item: Schedule) {
        _schedule.postValue(null)
        deleteScheduleUseCase.execute(item)
    }

    suspend fun createSchedule(item: Schedule) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultDeferred = async { createScheduleUseCase.execute(item) }
            _schedule.postValue(item)
        }
    }

    suspend fun getScheduleByIdUseCase(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultDeferred = async { getScheduleByIdUseCase.execute(filmId) }
            val result = resultDeferred.await()
            _schedule.postValue(result)
        }
    }
}