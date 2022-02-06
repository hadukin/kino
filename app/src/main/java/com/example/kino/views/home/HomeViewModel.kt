package com.example.kino.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kino.models.Content

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    private val _counter = MutableLiveData<Int>().apply {
        value = 0
    }
    val counter: LiveData<Int> = _counter

    private val _favoriteItems = MutableLiveData<ArrayList<Content>>().apply {
        value = arrayListOf()
    }

    val favoriteItems: LiveData<ArrayList<Content>> = _favoriteItems

    fun addToFavorite(item: Content) {
        _favoriteItems.value?.add(item)
    }

    fun removeFromFavorite(item: Content) {
        _favoriteItems.value?.remove(item)
    }

    fun increment() {
        _counter.value = _counter.value?.plus(1)
    }

}