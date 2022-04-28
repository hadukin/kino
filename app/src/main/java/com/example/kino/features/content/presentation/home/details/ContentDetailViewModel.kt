package com.example.kino.features.content.presentation.home.details

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.data.models.Schedule
import com.example.kino.features.content.domain.usecase.CreateScheduleUseCase
import com.example.kino.features.content.domain.usecase.DeleteFromFavoriteUseCase
import com.example.kino.features.content.domain.usecase.GetMoviePopularUseCase
import com.example.kino.features.content.domain.usecase.SaveToFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException


class ContentDetailViewModel(
    private val createScheduleUseCase: CreateScheduleUseCase,
) : ViewModel() {

    suspend fun createSchedule(schedule: Schedule) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultDeferred = async { createScheduleUseCase.execute(schedule) }
        }
    }
}