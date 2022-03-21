package com.example.kino.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kino.models.Movie

class HomeViewModel : ViewModel() {
    private val _favoriteItems = MutableLiveData<ArrayList<Movie>>().apply {
        value = arrayListOf()
    }

    val favoriteItems: LiveData<ArrayList<Movie>> = _favoriteItems

    fun addFavorite(item: Movie) {
        _favoriteItems.value?.add(item)
    }

    fun removeFavorite(item: Movie) {
        _favoriteItems.value?.remove(item)
    }

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