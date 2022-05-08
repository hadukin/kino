package com.example.kino

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.data.models.Schedule
import com.example.kino.features.content.domain.usecase.CreateScheduleUseCase
import com.example.kino.features.content.domain.usecase.DeleteFromFavoriteUseCase
import com.example.kino.features.content.domain.usecase.GetMoviePopularUseCase
import com.example.kino.features.content.domain.usecase.SaveToFavoriteUseCase
import com.example.kino.utils.NetworkConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(
    private val context: Context,
    private val getMoviePopularUseCase: GetMoviePopularUseCase,
    private val saveToFavoriteUseCase: SaveToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val createScheduleUseCase: CreateScheduleUseCase,
) : ViewModel() {

    // suspend fun createSchedule(schedule: Schedule) {
    //     viewModelScope.launch(Dispatchers.IO) {
    //         val resultDeferred = async { createScheduleUseCase.execute(schedule) }
    //     }
    // }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadMore(1)
        }
    }

    val isConnectedInternet = MutableLiveData<Boolean>().apply { value = false }

    val isNetworkAvailable = MutableLiveData<Boolean>().apply { value = false }

    private val _content = MutableLiveData<ArrayList<Movie>>().apply { value = arrayListOf() }
    val content: LiveData<ArrayList<Movie>> = _content

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun loadMore(page: Int, isReload: Boolean = false) =
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                if (isReload) {
                    _content.postValue(arrayListOf())
                }
                val resultDeferred = async { getMoviePopularUseCase.execute(page) }
                val result = resultDeferred.await()
                val data = arrayListOf<Movie>()

                for (item in result) {
                    if (content.value?.contains(item) == false) {
                        data.add(item)
                    }
                }
                val list = _content.value
                list?.addAll(result)
                _content.postValue(list)

            } catch (error: HttpException) {
                Log.e("ERROR_HTTP", "$error")
            } catch (error: Exception) {
                Log.e("ERROR_EXCEPTION", "$error")
            } finally {
                _isLoading.postValue(false)
            }
        }

    fun toggleFavorite(item: Movie): String {
        var current: Movie = item
        _content.value?.map {
            if (it.filmId == item.filmId) {
                current = it.apply {
                    isFavorite = !item.isFavorite
                }
            }
        }
        return if (current.isFavorite) {
            viewModelScope.launch(Dispatchers.IO) {
                saveToFavoriteUseCase.execute(current)
            }
            "Контент добавлен в избранное"
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                deleteFromFavoriteUseCase.execute(current)
            }
            "Контент удален из избранного"
        }
    }

    fun addFavorite(item: Movie) {
        _content.value?.map {
            if (it.filmId == item.filmId) {
                it.apply { isFavorite = true }
                viewModelScope.launch(Dispatchers.IO) {
                    saveToFavoriteUseCase.execute(it)
                }
            }
        }
    }

    fun removeFavorite(item: Movie) {
        _content.value?.map {
            if (it.filmId == item.filmId) {
                it.apply {
                    isFavorite = false
                }
                viewModelScope.launch(Dispatchers.IO) {
                    deleteFromFavoriteUseCase.execute(it)
                }
            }
        }
    }

    private val _page = MutableLiveData<Int>().apply { value = 1 }

    val page: LiveData<Int> = _page

    fun nextPage() {
        _page.value = _page.value?.plus(1)
    }
}