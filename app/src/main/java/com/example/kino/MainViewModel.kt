package com.example.kino

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kino.features.content.domain.usecase.GetMoviePopularUseCase
import com.example.kino.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainViewModel(private val getMoviePopularUseCase: GetMoviePopularUseCase) : ViewModel() {

    companion object {
        private const val TAG = "MovieViewModel"
    }

    init {
        viewModelScope.launch {
            loadMore(1)
        }
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared")
        super.onCleared()
    }

    private val _content = MutableLiveData<ArrayList<Movie>>().apply { value = arrayListOf() }
    val content: LiveData<ArrayList<Movie>> = _content
    val isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    suspend fun loadMore(page: Int) = coroutineScope {
        val result = getMoviePopularUseCase.execute(page)
        val data = arrayListOf<Movie>()
        for (item in result) {
            if (content.value?.contains(item) == false) {
                data.add(item)
            }
        }
        _content.postValue(data)
    }

    fun toggleFavorite(item: Movie): String {
        var current: Movie = item
        _content.value?.map {
            if (it.id == item.id) {
                current = it.apply {
                    isFavorite = !item.isFavorite
                }
            }
        }
        return if (current.isFavorite) {
            "Контент добавлен в избранное"
        } else {
            "Контент удален из избранного"
        }
    }


    fun addFavorite(item: Movie) {
        _content.value?.map {
            if (it.id == item.id) {
                it.apply {
                    isFavorite = true
                }
            }
        }
    }

    fun removeFavorite(item: Movie) {
        _content.value?.map {
            if (it.id == item.id) {
                it.apply {
                    isFavorite = false
                }
            }
        }
    }

    val favorites: ArrayList<Movie>
        get() = _content.value?.filter { it.isFavorite } as ArrayList<Movie>

    private val _page = MutableLiveData<Int>().apply { value = 1 }

    val page: LiveData<Int> = _page

    fun nextPage() {
        _page.value = _page.value?.plus(1)
    }
}