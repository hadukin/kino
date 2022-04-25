package com.example.kino.features.content.presentation.content_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kino.R
import com.example.kino.databinding.ContentItemBinding
import com.example.kino.databinding.MovieTitleBinding

class MovieRecyclerViewAdapter(
    private val listener: MovieRecyclerViewAdapter.MovieClickListener
) : RecyclerView.Adapter<MovieRecyclerViewHolder>() {

    fun setMovieList(movies: ArrayList<MovieRecyclerViewItem.Movie>) {
        this.items.addAll(movies)
        notifyDataSetChanged()
    }

    var items = arrayListOf<MovieRecyclerViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRecyclerViewHolder {
        return when (viewType) {
            R.layout.movie_title -> MovieRecyclerViewHolder.TitleViewHolder(
                MovieTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.content_item -> MovieRecyclerViewHolder.MovieViewHolder(
                ContentItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: MovieRecyclerViewHolder, position: Int) {
        when (holder) {
            is MovieRecyclerViewHolder.TitleViewHolder -> holder.bind(items[position] as MovieRecyclerViewItem.Title)
            is MovieRecyclerViewHolder.MovieViewHolder -> holder.bind(
                items[position] as MovieRecyclerViewItem.Movie,
                listener
            )
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MovieRecyclerViewItem.Title -> R.layout.movie_title
            is MovieRecyclerViewItem.Movie -> R.layout.content_item
        }
    }

    interface MovieClickListener {
        fun onClickDetails(contentItem: MovieRecyclerViewItem.Movie, position: Int)
        fun onClickFavorite(contentItem: MovieRecyclerViewItem.Movie, position: Int)
    }

    fun toggleFavorite(position: Int): String {
        // items[position] = items[position].copy(isFavorite = !items[position].isFavorite)
        //
        // notifyItemChanged(position)
        // return if (items[position].isFavorite) {
        //     "Контент добавлен в избранное"
        // } else {
        //     "Контент удален из избранного"
        // }
        return "toggle"
    }

    fun addFavorite(item: MovieRecyclerViewItem.Movie, position: Int): String {
        items.add(position, item)
        notifyItemInserted(position)
        return "Контент добавлен в избранное"
    }

    fun removeFavorite(item: MovieRecyclerViewItem.Movie, position: Int): String {
        items.removeAt(position)
        notifyItemRangeRemoved(position, 1)
        return "Контент удален из избранного"
    }
}