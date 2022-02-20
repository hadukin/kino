package com.example.kino.views.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kino.App
import com.example.kino.models.Content
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _favoriteItems = MutableLiveData<ArrayList<Content>>().apply {
        value = arrayListOf()
    }

    val favoriteItems: LiveData<ArrayList<Content>> = _favoriteItems

    fun addFavorite(item: Content) {
        _favoriteItems.value?.add(item)
    }

    fun removeFavorite(item: Content) {
        _favoriteItems.value?.remove(item)
    }

    private val _contentList = MutableLiveData<ArrayList<Content>>().apply {
        value = arrayListOf()
    }

    val contentList: LiveData<ArrayList<Content>> = _contentList

    private val _page = MutableLiveData<Int>().apply { value = 1 }

    val page: LiveData<Int> = _page

    fun nextPage() {
        _page.value = _page.value?.plus(1)
    }
}