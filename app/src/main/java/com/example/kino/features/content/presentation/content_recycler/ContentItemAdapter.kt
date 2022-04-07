package com.example.kino.features.content.presentation.content_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.R
import com.example.kino.features.content.data.models.Movie

class ContentItemAdapter(
    private val listener: ContentClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
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
        }
    }

    override fun getItemCount(): Int {
        return movies.size
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