package com.example.kino.views.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kino.App
import com.example.kino.models.Movie
import com.example.kino.models.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    // fun toggleFavorite(item: Movie) {
    //     _contentList.value?.map {
    //         if (it.id == item.id) {
    //             item.apply {
    //                 isFavorite = !item.isFavorite
    //             }
    //         }
    //     }
    // }
    //
    //
    // fun addFavorite(item: Movie) {
    //     _contentList.value?.map {
    //         if (it.id == item.id) {
    //             item.apply {
    //                 isFavorite = true
    //             }
    //         }
    //     }
    // }
    //
    // fun removeFavorite(item: Movie) {
    //     _contentList.value?.map {
    //         if (it.id == item.id) {
    //             item.apply {
    //                 isFavorite = false
    //             }
    //         }
    //     }
    // }
    //
    // private val _contentList = MutableLiveData<ArrayList<Movie>>().apply {
    //     value = arrayListOf()
    // }
    //
    // val contentList: LiveData<ArrayList<Movie>> = _contentList
    //
    // private val _page = MutableLiveData<Int>().apply { value = 1 }
    //
    // val page: LiveData<Int> = _page
    //
    // fun nextPage() {
    //     _page.value = _page.value?.plus(1)
    // }

}