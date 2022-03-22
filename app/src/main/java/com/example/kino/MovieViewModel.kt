package com.example.kino

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kino.models.Movie

class MovieViewModel : ViewModel() {

    fun toggleFavorite(item: Movie): String {
        var current: Movie = item
        _contentList.value?.map {
            if (it.id == item.id) {
                current = it.apply {
                    isFavorite = !item.isFavorite
                }
            }
        }
        if (current.isFavorite) {
            return "Контент добавлен в избранное"
        } else {
            return "Контент удален из избранного"
        }
    }


    fun addFavorite(item: Movie) {
        _contentList.value?.map {
            if (it.id == item.id) {
                it.apply {
                    isFavorite = true
                }
            }
        }
    }

    fun removeFavorite(item: Movie) {
        _contentList.value?.map {
            if (it.id == item.id) {
                it.apply {
                    isFavorite = false
                }
            }
        }
    }

    val favorites: ArrayList<Movie>
        get() = _contentList.value?.filter { it.isFavorite } as ArrayList<Movie>

    private val _contentList = MutableLiveData<ArrayList<Movie>>().apply {
        value = arrayListOf()
    }

    val contentList: LiveData<ArrayList<Movie>> = _contentList

    private val _page = MutableLiveData<Int>().apply { value = 1 }

    val page: LiveData<Int> = _page

    fun nextPage() {
        _page.value = _page.value?.plus(1)
    }
}
