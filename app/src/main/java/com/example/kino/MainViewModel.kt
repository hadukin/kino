package com.example.kino

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.usecase.DeleteFromFavoriteUseCase
import com.example.kino.features.content.domain.usecase.GetMoviePopularUseCase
import com.example.kino.features.content.domain.usecase.SaveToFavoriteUseCase
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val getMoviePopularUseCase: GetMoviePopularUseCase,
    private val saveToFavoriteUseCase: SaveToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadMore(1)
        }
    }

    private val _content = MutableLiveData<ArrayList<Movie>>().apply { value = arrayListOf() }
    val content: LiveData<ArrayList<Movie>> = _content

    suspend fun loadMore(page: Int) = coroutineScope {
        val result = getMoviePopularUseCase.execute(page)
        val data = arrayListOf<Movie>()

        for (item in result) {
            if (content.value?.contains(item) == false) {
                data.add(item)
            }
        }
        val list = _content.value
        list?.addAll(result)
        _content.postValue(list)
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
            if (it.id == item.id) {
                it.apply { isFavorite = true }
                viewModelScope.launch(Dispatchers.IO) {
                    saveToFavoriteUseCase.execute(it)
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

    companion object {
        private const val TAG = "MovieViewModel"
    }
}