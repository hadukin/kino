package com.example.kino.features.content.presentation.content_recycler

import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.R
import com.example.kino.features.content.data.models.Movie

class ContentItemAdapter(
    private val listener: ContentClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_MOVIE = 0
        const val TYPE_LOADER = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            TYPE_MOVIE -> {
                return ContentItemViewHolder(inflater.inflate(R.layout.content_item, parent, false))
            }
            TYPE_LOADER -> {
                return LoadingViewHolder(inflater.inflate(R.layout.loader_item, parent, false))
            }
        }

        return ContentItemViewHolder(inflater.inflate(R.layout.content_item, parent, false))
    }

    var movies = arrayListOf<Movie>()

    fun setMovieList(movies: ArrayList<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContentItemViewHolder -> {
                holder.bind(movies[position], listener)
            }
            is LoadingViewHolder -> {
                holder.bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            movies[position] is Movie -> TYPE_MOVIE
            else -> TYPE_LOADER
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addLoadingView() {
        Handler().post {
            notifyItemInserted(movies.size - 1)
        }
    }

    fun removeLoadingView() {
        movies.removeAt(movies.size - 1)
        notifyItemRemoved(movies.size)
    }

    interface ContentClickListener {
        fun onClickDetails(contentItem: Movie, position: Int)
        fun onClickFavorite(contentItem: Movie, position: Int)
    }

    fun toggleFavorite(position: Int): String {
        movies[position] = movies[position].copy(isFavorite = !movies[position].isFavorite)

        notifyItemChanged(position)
        return if (movies[position].isFavorite) {
            "Контент добавлен в избранное"
        } else {
            "Контент удален из избранного"
        }
    }

    fun addFavorite(item: Movie, position: Int): String {
        movies.add(position, item)
        notifyItemInserted(position)
        return "Контент добавлен в избранное"
    }

    fun removeFavorite(item: Movie, position: Int): String {
        movies.removeAt(position)
        notifyItemRangeRemoved(position, 1)
        return "Контент удален из избранного"
    }
}