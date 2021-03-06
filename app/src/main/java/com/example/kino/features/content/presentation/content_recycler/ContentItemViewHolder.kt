package com.example.kino.features.content.presentation.content_recycler

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kino.R
import com.example.kino.features.content.data.models.Movie

class ContentItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val description = itemView.findViewById<TextView>(R.id.description)
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val buttonDetail = itemView.findViewById<TextView>(R.id.button)
    private val buttonFavorite = itemView.findViewById<ImageView>(R.id.favorite)
    private val voteCountText = itemView.findViewById<TextView>(R.id.vote_count)

    fun bind(item: Movie, listener: ContentItemAdapter.ContentClickListener) {
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




