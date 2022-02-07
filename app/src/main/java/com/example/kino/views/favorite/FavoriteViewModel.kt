package com.example.kino.views.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kino.models.Content

class FavoriteViewModel : ViewModel() {
    private val _favoriteItems = MutableLiveData<ArrayList<Content>>().apply {
        value = arrayListOf()
    }

    val favoriteItems: LiveData<ArrayList<Content>> = _favoriteItems
}