package com.example.kino.features.content.presentation.content_recycler

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.kino.R
import com.example.kino.databinding.ContentItemBinding
import com.example.kino.databinding.MovieTitleBinding

// import com.example.kino.features.content.data.models.Movie

sealed class MovieRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    class TitleViewHolder(private val binding: MovieTitleBinding) :
        MovieRecyclerViewHolder(binding) {
        fun bind(item: MovieRecyclerViewItem.Title) {

        }
    }

    class MovieViewHolder(private val binding: ContentItemBinding) :
        MovieRecyclerViewHolder(binding) {
        fun bind(
            item: MovieRecyclerViewItem.Movie,
            listener: MovieRecyclerViewAdapter.MovieClickListener
        ) {

            // val title = itemView.findViewById<TextView>(R.id.title)
            // val description = itemView.findViewById<TextView>(R.id.description)
            // val image = itemView.findViewById<ImageView>(R.id.image)
            // val buttonDetail = itemView.findViewById<TextView>(R.id.button)
            // val buttonFavorite = itemView.findViewById<ImageView>(R.id.favorite)
            // val voteCountText = itemView.findViewById<TextView>(R.id.vote_count)

            val title = binding.title
            val description = binding.description
            val image = binding.image
            val buttonDetail = binding.button
            val buttonFavorite = binding.favorite
            val voteCountText = binding.voteCount



            if (item.isFavorite) {
                buttonFavorite.setColorFilter(Color.RED)
            } else {
                buttonFavorite.setColorFilter(Color.GRAY)
            }

            title.apply {
                text = item.nameRu
            }

            description.apply {
                text = item.nameRu
                maxLines = 2
            }

            Glide.with(image.context)
                .load(item.posterUrl)
                .centerCrop()
                .into(image)

            buttonDetail.setOnClickListener {
                listener.onClickDetails(item, adapterPosition)
            }

            buttonFavorite.setOnClickListener {
                listener.onClickFavorite(item, adapterPosition)
            }
        }
    }
}