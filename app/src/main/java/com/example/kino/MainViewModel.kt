package com.example.kino

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kino.features.content.data.datasource.ContentRemoteDataSourceImpl
import com.example.kino.features.content.data.repository.ContentRepositoryImpl
import com.example.kino.features.content.domain.usecase.GetMoviePopularUseCase
import com.example.kino.models.Movie

class MainViewModel(private val getMoviePopularUseCase: GetMoviePopularUseCase) : ViewModel() {

    companion object {
        private const val TAG = "MovieViewModel"
    }

    // private var repo: ContentRepositoryImpl =
    //     ContentRepositoryImpl(ContentRemoteDataSourceImpl(App.instance.movieClient))


    init {
        Log.d(TAG, "init")
        // TODO: initial loading content
        // viewModelScope.launch(Dispatchers.IO) {
        //     val result = repo.getMoviePopular(1, App.API_KEY)
        //     if (result != null) {
        //         Log.d("VMODELRESULT", "${result.size}")
        //         contentList.value?.addAll(result)
        //     }
        // }
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared")
        super.onCleared()
    }

    // suspend fun getMovies() {
    //     val repo = ContentRepositoryImpl(ContentRemoteDataSourceImpl(App.instance.movieClient))
    //     val result = repo.getMoviePopular(1, App.API_KEY)
    // }

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

    fun loadMore() {

    }
}
