package com.example.kino

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kino.features.content.data.datasource.ContentRemoteDataSourceImpl
import com.example.kino.features.content.data.repository.ContentRepositoryImpl
import com.example.kino.features.content.domain.repository.ContentRepository
import com.example.kino.features.content.domain.usecase.GetMoviePopularUseCase

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val contentRepository: ContentRepository by lazy {
        ContentRepositoryImpl(
            context = context,
            remote = ContentRemoteDataSourceImpl(App.instance.movieClient)
        )
    }

    private val getMoviePopularUseCase by lazy { GetMoviePopularUseCase(contentRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getMoviePopularUseCase = getMoviePopularUseCase) as T
    }

}
