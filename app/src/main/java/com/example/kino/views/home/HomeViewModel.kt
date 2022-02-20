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

    fun addContent(item: Content) {
        _contentList.value?.add(item)
    }

    //
    // val contentList: LiveData<ArrayList<Content>> = _favoriteItems

    // private val _users: MutableLiveData<List<Content>> by lazy {
    //     MutableLiveData<List<Content>>().also {
    //         loadUsers()
    //     }
    // }
    //
    // fun fetchUsers(): LiveData<List<Content>> {
    //     return _users
    // }
    //
    // private fun loadUsers(): MutableList<Content> {
    //     val data = mutableListOf<Content>()
    //
    //     App.instance.contentApi.getContent(1, 10).enqueue(object : Callback<List<Content>?> {
    //         override fun onResponse(
    //             call: Call<List<Content>?>,
    //             response: Response<List<Content>?>
    //         ) {
    //             response.body().let {
    //                 if (it != null) {
    //                     data.addAll(it)
    //                 }
    //             }
    //         }
    //
    //         override fun onFailure(call: Call<List<Content>?>, t: Throwable) {
    //             Log.d("RESULT", "ERROR: ${t}")
    //         }
    //     })
    //     return data
    // }

    // fun fetchContent(): MutableList<Content> {
    //     val data = mutableListOf<Content>()
    // }
}