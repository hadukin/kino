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
import okhttp3.internal.notify

class MainViewModel(private val getMoviePopularUseCase: GetMoviePopularUseCase) : ViewModel() {

    companion object {
        private const val TAG = "MovieViewModel"
    }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            loadMore(1, App.API_KEY)
            // val result = getMoviePopularUseCase.execute(1, App.API_KEY)
            // content.postValue(result.toMutableList())
        }
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared")
        super.onCleared()
    }

    private val _content = MutableLiveData<ArrayList<Movie>>().apply { value = arrayListOf() }
    val content: LiveData<ArrayList<Movie>> = _content
    val isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    suspend fun loadMore(page: Int, apiKey: String) = coroutineScope {
        launch {
            val result = getMoviePopularUseCase.execute(page, App.API_KEY)
            _content.postValue(result as ArrayList<Movie>?)
            _content.value?.addAll(result as ArrayList<Movie>)
        }
    }

    // private suspend fun loadMoreContent(page: Int, apiKey: String) = coroutineScope {
    //     launch {
    //         val result = getMoviePopularUseCase.execute(page, App.API_KEY)
    //         content.postValue(result.toMutableList())
    //     }
    // }

    fun toggleFavorite(item: Movie): String {
        // var current: Movie = item
        // _contentList.value?.map {
        //     if (it.id == item.id) {
        //         current = it.apply {
        //             isFavorite = !item.isFavorite
        //         }
        //     }
        // }
        // if (current.isFavorite) {
        //     return "Контент добавлен в избранное"
        // } else {
        //     return "Контент удален из избранного"
        // }
        return ""
    }


    fun addFavorite(item: Movie) {
        // _contentList.value?.map {
        //     if (it.id == item.id) {
        //         it.apply {
        //             isFavorite = true
        //         }
        //     }
        // }
    }

    fun removeFavorite(item: Movie) {
        // _contentList.value?.map {
        //     if (it.id == item.id) {
        //         it.apply {
        //             isFavorite = false
        //         }
        //     }
        // }
    }

    val favorites: ArrayList<Movie>
        get() = _content.value?.filter { it.isFavorite } as ArrayList<Movie>

    // private val _contentList = MutableLiveData<ArrayList<Movie>>().apply {
    //     value = arrayListOf()
    // }
    // val contentList: LiveData<ArrayList<Movie>> = _contentList

    private val _page = MutableLiveData<Int>().apply { value = 1 }

    val page: LiveData<Int> = _page

    fun nextPage() {
        _page.value = _page.value?.plus(1)
    }
}
