package com.example.kino.features.content.presentation.home.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kino.features.content.data.models.Schedule
import com.example.kino.features.content.domain.usecase.CreateScheduleUseCase
import com.example.kino.features.content.domain.usecase.GetScheduleByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class ContentDetailViewModel(
    private val createScheduleUseCase: CreateScheduleUseCase,
    private val getScheduleByIdUseCase: GetScheduleByIdUseCase,
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val schedule = MutableLiveData<Schedule>()

    suspend fun createSchedule(item: Schedule) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultDeferred = async { createScheduleUseCase.execute(item) }
            schedule.postValue(item)
        }
    }

    suspend fun getScheduleByIdUseCase(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultDeferred = async { getScheduleByIdUseCase.execute(filmId) }
            val result = resultDeferred.await()
            Log.d("!!!!", "${result}")
            schedule.postValue(result)
        }
    }
}