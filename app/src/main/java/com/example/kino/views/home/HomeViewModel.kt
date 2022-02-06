package com.example.kino.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kino.models.Content

class HomeViewModel : ViewModel() {
    private val _favoriteItems = MutableLiveData<ArrayList<Content>>().apply {
        value = arrayListOf()
    }

    val favoriteItems: LiveData<ArrayList<Content>> = _favoriteItems

    fun update() {}

    fun addFavorite(item: Content) {
        _favoriteItems.value?.add(item)
    }

    fun removeFavorite(item: Content) {
        _favoriteItems.value?.remove(item)
    }
}